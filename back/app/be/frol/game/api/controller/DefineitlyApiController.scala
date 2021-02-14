package be.frol.game.api.controller

import be.frol.game.api.model.{DefineItLyGame, StringWrapper}
import be.frol.game.error.{FunctionalError, IllegalPlay}
import be.frol.game.mapper.DilMapper
import be.frol.game.model.DilRichGame
import be.frol.game.repository.{FileRepository, GameRepository, UserRepository}
import be.frol.game.tables.Tables
import be.frol.game.tables.Tables._
import be.frol.game.utils.DateUtils
import be.frol.game.utils.OptionUtils._
import be.frol.game.{DbContext, ParentController}
import com.google.inject.{Inject, Singleton}
import play.api.db.slick.DatabaseConfigProvider
import play.api.libs.json.Reads
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

  def addQuestion(uuid: String) = updateGame[StringWrapper](uuid) { (r, u, request) =>
    if (r.nextRound.flatMap(_.userId).map(_ == u.id).getOrElse(true))
      DilRound += DilRoundRow(0L, randomUuid(), r.game.id, u.id, request.body.value, DateUtils.ts.toOpt)
    else throw new IllegalPlay
  }


  def addResponse(uuid: String, questionUuid: String) = updateGame[StringWrapper](uuid) { (r,u,request) =>
      Tables.DilResponse += Tables.DilResponseRow(0l, randomUuid(), r.game.id, u.id,
        r.round(questionUuid).flatMap(_.round).get.id, request.body.value, DateUtils.ts.toOpt)
  }

  def choseResponse(uuid: String, questionUuid: String) = updateGame[StringWrapper](uuid) { (r,u,request) =>
    val round = r.round(questionUuid).get
    if(round.userId.get == u.id) throw new IllegalPlay
    if(round.response(request.body.value).map(_.fkUserId).get == u.id) throw new IllegalPlay
    Tables.DilChoice += Tables.DilChoiceRow(0l, randomUuid(), r.game.id, u.id,
      round.round.get.id, round.response(request.body.value).get.id, DateUtils.ts.toOpt)
  }

  def getGame(uuid: String) = run { implicit request =>
    db.run(getCurrentGameDto(uuid, withHistory=request.getQueryString("withHistory").map(_.toBoolean).getOrElse(false)))
  }

  private def updateGame[T](uuid: String)(f: (DilRichGame, UserRow, Request[T]) => DBIO[_])(implicit r:Reads[T]) =
    runWithInput[T, DefineItLyGame] { implicit request =>
      db.run(
        currentUser.flatMap { u =>
          getCurrentGameInfo(uuid).flatMap { r =>
            f(r, u, request)
          }.flatMap(_ => getCurrentGameDto(uuid))
        }
      )
    }

  private def getCurrentGameDto(uuid: String, withHistory:Boolean = false)(implicit request: Request[_]) = {
    getCurrentGameInfo(uuid, withHistory).map(toDto)
  }

  private def getCurrentGameInfo(uuid: String, withHistory:Boolean=false)(implicit request: Request[_]): DBIO[DilRichGame] = {
    def roundRequest(v: Tables.GameRow) = {
      if(withHistory) DilRound.filter(_.fkGameId === v.id)
      else DilRound.filter(_.fkGameId === v.id).filter(
        (DilRound.filter(_.fkGameId === v.id).map(_.id).max === _.id)
      )
    }

    currentUser.flatMap { u =>
      Game.filter(_.uuid === uuid).result.head
        .flatMap {
          v =>
            UserInGame.filter(_.fkGameId === v.id).join(User).on(_.fkUserId === _.id).result
              .zipWith(roundRequest(v).result)(_ -> _)
              .zipWith(DilResponse.filter(_.fkGameId === v.id).result)(_ -> _)
              .zipWith(DilChoice.filter(_.fkGameId === v.id).result)(_ -> _)
              .map(v -> _)
        }.map {
        case (g, (((u, rounds), responses), choices)) =>
          DilRichGame.build(g, u.map(_.swap), rounds, responses, choices)
      }
        .map {
          case dilg if !dilg.game.userInGame(currentUserUUID) => throw new FunctionalError("Not in game")
          case dilg if !dilg.game.gameStarted => throw new FunctionalError("game not started")
          case dilg => dilg
        }
    }
  }

  private def toDto(g: DilRichGame) = DilMapper.toDto(g)

}

