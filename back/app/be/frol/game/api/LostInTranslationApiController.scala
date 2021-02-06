package be.frol.game.api

import be.frol.game.error.FunctionalError
import be.frol.game.mapper.LitGameMapper.toLitDto
import be.frol.game.model.{LitRichGame, LostInTranslationGame, RichGame}
import be.frol.game.repository.{GameRepository, LitRepository}
import be.frol.game.tables.Tables
import be.frol.game.tables.Tables._
import be.frol.game.{DbContext, ParentController}
import com.google.inject.{Inject, Singleton}
import play.api.db.slick.DatabaseConfigProvider
import play.api.mvc.{ControllerComponents, Request}

import scala.concurrent.ExecutionContext
import be.frol.game.utils.OptionUtils._

@Singleton
class LostInTranslationApiController @Inject()(
                                                dbProvider: DatabaseConfigProvider,
                                                val cc: ControllerComponents,
                                                val gameService: GameRepository,
                                                val litRepository: LitRepository,
                                              )(implicit
                                                executionContext: ExecutionContext
                                              )
  extends ParentController(cc, dbProvider) with DbContext {

  import api._

  def addDrawingRound(uuid: String) = ???

  def addTextRound(uuid: String) = runWithInput[String,LostInTranslationGame](implicit request => db.run(
    getLitGame(uuid).flatMap(
      litg => litRepository.add(litg.nextRoundFor(currentUserUUID).map(deriveRound(litg, _, currentUserUUID, Some(request.body), None))
        .getOrThrow("no round to play"))
    ).flatMap(_ => getCurrentGameInfo(uuid))
  ))

  def deriveRound(litg: LitRichGame, fromRound: LitRoundRow, currentUserUuid: String, text: Option[String], fileId: Option[String]) =
    fromRound.copy(id = 0, fkUserId = litg.user(currentUserUuid).get.u.id, text = text, fkFileId = fileId)

  def getAllRounds(g: RichGame) = Tables.LitRound.filter(_.fkGameId === g.id).result

  def getGame(uuid: String) = run { implicit request =>
    db.run(
      getCurrentGameInfo(uuid)
    )
  }

  private def getCurrentGameInfo(uuid: String)(implicit request:Request[_]) = {
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

