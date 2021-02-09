package be.frol.game.api

import be.frol.game.error.FunctionalError
import be.frol.game.mapper.LitGameMapper.toLitDto
import be.frol.game.model.LostInTranslationRound.RoundType
import be.frol.game.model.{LitRichGame, LostInTranslationGame, LostInTranslationRound, RichGame}
import be.frol.game.repository.{FileRepository, GameRepository, LitRepository}
import be.frol.game.tables.Tables
import be.frol.game.utils.DateUtils
import be.frol.game.utils.OptionUtils._
import be.frol.game.{DbContext, ParentController, tables}
import com.google.inject.{Inject, Singleton}
import play.api.db.slick.DatabaseConfigProvider
import play.api.libs.json.Json
import play.api.mvc.{ControllerComponents, Request}

import java.nio.file.Files
import javax.sql.rowset.serial.SerialBlob
import scala.concurrent.ExecutionContext

@Singleton
class LostInTranslationApiController @Inject()(
                                                dbProvider: DatabaseConfigProvider,
                                                val cc: ControllerComponents,
                                                val gameService: GameRepository,
                                                val litRepository: LitRepository,
                                                val fileRepository: FileRepository,
                                              )(implicit
                                                executionContext: ExecutionContext
                                              )
  extends ParentController(cc, dbProvider) with DbContext {

  import api._

  def addDrawingRound(gameUuid: String, storyId: String) = Action.async(parse.multipartFormData) { implicit request =>
    val file = request.body.file("file").getOrThrow("missing file")
    val fileUuid = uuid
    db.run(
      currentUser.flatMap(u =>
        fileRepository.add(new tables.Tables.FileRow(
          fileUuid,
          new SerialBlob(Files.readAllBytes(file.ref.path)),
          file.filename,
          file.contentType.getOrElse(""),
          u.id, DateUtils.ts))
      ).flatMap(f => playRound(gameUuid, storyId, None, Some(fileUuid)))
        .transactionally
    ).map(Json.toJson(_)).map(Ok(_))
  }

  def addTextRound(gameUuid: String, storyId: String) = runWithInput[String, LostInTranslationGame](implicit request =>
    db.run(playRound(gameUuid, storyId, Some(request.body), None).transactionally)
  )

  private def playRound(gameUuid: String, storyId: String, text: Option[String], fileId: Option[String])(implicit request: Request[_]) = {
    getLitGame(gameUuid).flatMap(litg =>
      litRepository.add(
        litg.nextRoundsFor(currentUserUUID)
          .find(_.round.storyId == storyId)
          .filter(r => (r.roundType == RoundType.Text && text.isDefined) || (r.roundType == RoundType.Drawing && fileId.isDefined))
          .filter(_ => text.isDefined != fileId.isDefined)
          .map(_.round.copy(text = text, fkFileId = fileId, timestamp = Option(DateUtils.ts)))
          .getOrThrow("not a legal play"))
    ).flatMap(_ => getCurrentGameInfo(uuid))

  }

  def getAllRounds(g: RichGame) = Tables.LitRound.filter(_.fkGameId === g.id).result

  def getGame(uuid: String) = run { implicit request =>
    db.run(
      getCurrentGameInfo(uuid)
    )
  }

  private def getCurrentGameInfo(uuid: String)(implicit request: Request[_]) = {
    currentUser.flatMap { u =>
      getLitGame(uuid)
        .flatMap(markFinishedIfNecessary(_))
        .map {
          case litg if !litg.game.userInGame(currentUserUUID) => throw new FunctionalError("Not in game")
          case litg if !litg.game.gameStarted => throw new FunctionalError("game not started")
          case litg if litg.finished => toLitDto(litg)
          case litg => toLitDto(litg, v =>
            if (v.round.fkUserId == u.id || v.nextPlayer(litg).id == u.id) v
            else v.copy(round = v.round.copy(text = None, fkFileId = None)))
        }
    }
  }

  private def getLitGame(uuid: String) = {
    gameService.getGame(uuid).map(RichGame.build)
      .flatMap(g => Tables.LitRound.filter(_.fkGameId === g.id).result.map(rounds => LitRichGame(g, rounds)))
  }


  def markFinishedIfNecessary(litg: LitRichGame): DBIO[LitRichGame] = {
    if (litg.haveToMarkGameFinished) gameService.markFinished(litg.game).flatMap(_ => getLitGame(litg.game.game.uuid))
    else DBIO.successful(litg)
  }

}

