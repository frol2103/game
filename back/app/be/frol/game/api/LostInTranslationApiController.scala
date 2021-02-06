package be.frol.game.api

import be.frol.game.error.FunctionalError
import be.frol.game.mapper.LitGameMapper.toLitDto
import be.frol.game.model.{LitRichGame, LostInTranslationGame, RichGame}
import be.frol.game.repository.{FileRepository, GameRepository, LitRepository}
import be.frol.game.tables.Tables
import be.frol.game.tables.Tables._
import be.frol.game.utils.DateUtils
import be.frol.game.utils.OptionUtils._
import be.frol.game.{DbContext, ParentController, tables}
import com.google.inject.{Inject, Singleton}
import play.api.db.slick.DatabaseConfigProvider
import play.api.libs.json.Json
import play.api.mvc.{ControllerComponents, Request}

import java.nio.file.Files
import java.util.UUID
import javax.sql.rowset.serial.SerialBlob
import scala.concurrent.{ExecutionContext, Future}

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

  def addDrawingRound(uuid: String) = Action.async(parse.multipartFormData) { implicit request =>
    val file = request.body.file("file").getOrThrow("missing file")
    val fileUuid=uuid
    db.run(
      currentUser.flatMap(u =>
        fileRepository.add(new tables.Tables.FileRow(
          fileUuid,
          new SerialBlob(Files.readAllBytes(file.ref.path)),
          file.filename,
          file.contentType.getOrElse(""),
          u.id, DateUtils.ts))
      ).flatMap(f => playRound(uuid, None, Some(fileUuid)))
        .transactionally
    ).map(Json.toJson(_)).map(Ok(_))
  }

  def addTextRound(uuid: String) = runWithInput[String, LostInTranslationGame](implicit request =>
    db.run(playRound(uuid, Some(request.body), None).transactionally)
  )

  private def playRound(uuid: String, text: Option[String], fileId: Option[String])(implicit request: Request[_]) = {
    getLitGame(uuid).flatMap(
      litg => litRepository.add(litg.nextRoundFor(currentUserUUID).map(deriveRound(litg, _, currentUserUUID, text, fileId))
        .getOrThrow("no round to play"))
    ).flatMap(_ => getCurrentGameInfo(uuid))

  }

  def deriveRound(litg: LitRichGame, fromRound: LitRoundRow, currentUserUuid: String, text: Option[String], fileId: Option[String]) =
    fromRound.copy(id = 0, fkUserId = litg.user(currentUserUuid).get.u.id, text = text, fkFileId = fileId)

  def getAllRounds(g: RichGame) = Tables.LitRound.filter(_.fkGameId === g.id).result

  def getGame(uuid: String) = run { implicit request =>
    db.run(
      getCurrentGameInfo(uuid)
    )
  }

  private def getCurrentGameInfo(uuid: String)(implicit request: Request[_]) = {
    getLitGame(uuid)
      .flatMap(markFinishedIfNecessary(_))
      .map {
        case litg if !litg.game.userInGame(currentUserUUID) => throw new FunctionalError("Not in game")
        case litg if !litg.game.gameStarted => throw new FunctionalError("game not started")
        case litg if litg.finished => toLitDto(litg, Option(litg.rounds))
        case litg => toLitDto(litg, litg.nextRoundFor(currentUserUUID).map(r => List(r)))
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

