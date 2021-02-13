package be.frol.game.api

import be.frol.game.error.FunctionalError
import be.frol.game.mapper.DilMapper
import be.frol.game.mapper.LitGameMapper.toLitDto
import be.frol.game.model.DilRichGame
import be.frol.game.repository.{FileRepository, GameRepository, UserRepository}
import be.frol.game.tables.Tables._
import be.frol.game.{DbContext, ParentController}
import com.google.inject.{Inject, Singleton}
import play.api.db.slick.DatabaseConfigProvider
import play.api.mvc.{ControllerComponents, Request}

import scala.concurrent.ExecutionContext

@Singleton
class DefineitlyApiController @Inject()(
                                         dbProvider: DatabaseConfigProvider,
                                         val cc: ControllerComponents,
                                         val gameService: GameRepository,
                                         val fileRepository: FileRepository,
                                       )(implicit
                                         executionContext: ExecutionContext,
                                         userRepository: UserRepository,
                                       )
  extends ParentController(cc, dbProvider) with DbContext {

  import api._

  def addQuestion(uuid: String) =

  def addResponse(uuid: String, questionUuid: String) = ???

  def choseResponse(uuid: String, questionUuid: String) = ???

  def getGame(uuid: String) = run {
    db.run(getCurrentGameInfo(uuid))
      .map(DilMapper.toDto)
  }

  def getDilGame(uuid: String) = {
    Game.filter(_.uuid === uuid).result.head
      .flatMap {
        v =>
          UserInGame.filter(_.fkGameId === v.id).join(Game).on(_.fkGameId === _.id).result
            .zipWith(DilRound.filter(_.fkGameId === v.id).result)(_ -> _)
            .zipWith(DilResponse.filter(_.fkGameId === v.id).result)(_ -> _)
            .zipWith(DilChoice.filter(_.fkGameId === v.id).result)(_ -> _)
            .map(v -> _)
      }.map {
      case (g, (((u, rounds), responses), choices)) =>
        DilRichGame.build(g, u, rounds, responses, choices)
    }
  }

  private def getCurrentGameInfo(uuid: String)(implicit request: Request[_]) : DilRichGame = {
    currentUser.flatMap { u =>
      getDilGame(uuid)
        .map {
          case dilg if !dilg.game.userInGame(currentUserUUID) => throw new FunctionalError("Not in game")
          case dilg if !dilg.game.gameStarted => throw new FunctionalError("game not started")
          case dilg => toLitDto(dilg, v =>
            if (v.round.fkUserId == u.id || v.nextPlayer(dilg).id == u.id) v
            else v.copy(round = v.round.copy(text = None, fkFileId = None)))
        }
    }
  }

}

