package be.frol.game.repository

import be.frol.game.DbContext
import be.frol.game.api.model.GameDescription
import be.frol.game.error.FunctionalError
import be.frol.game.model.RichGame
import be.frol.game.tables.Tables
import be.frol.game.tables.Tables._
import be.frol.game.utils.DateUtils
import com.google.inject.Inject
import play.api.db.slick.DatabaseConfigProvider

import scala.concurrent.ExecutionContext

class GameRepository @Inject()(
                                val dbConfigProvider: DatabaseConfigProvider,
                              )(implicit executionContext: ExecutionContext)
  extends DbContext {

  import api._

  def add(p: Tables.GameRow)(implicit executionContext: ExecutionContext) = {
    ((Tables.Game returning Tables.Game.map(_.id)
      into ((v, id) => v.copy(id = id))) += p)
  }

  def update(g: Tables.GameRow) = Tables.Game.filter(_.id === g.id).update(g)

  def add(p: Tables.UserInGameRow)(implicit executionContext: ExecutionContext) = {
    ((Tables.UserInGame returning Tables.UserInGame.map(_.id)
      into ((v, id) => v.copy(id = id))) += p)
  }

  def initGame(gameType: String, user: Tables.UserRow) = {
    for {
      g <- add(Tables.GameRow(0L, randomUuid, gameType, DateUtils.ts, GameDescription.Status.ToStart.toString))
      u <- add(Tables.UserInGameRow(0L, g.id, user.id, Option("ADMIN")))
    } yield g -> List(u -> user)
  }

  def getGame(uuid: String) = {
    for {
      g <- Tables.Game.filter(_.uuid === uuid).result.head
      u <- Tables.UserInGame.filter(_.fkGameId === g.id).join(Tables.User).on { case (g, u) => g.fkUserId === u.id }.result
    } yield (g -> u.toList)

  }

  def getAllGamesFor(user: Tables.UserRow) = {
    Tables.Game.join(Tables.UserInGame).on { case (g, u) => g.id === u.fkGameId }.filter(_._2.fkUserId === user.id).map(_._1).result
  }

  def join(gameUuid: String, user: UserRow) = {
    val alreadyInGame = Tables.UserInGame.join(Tables.Game).on { (u, g) => u.fkGameId === g.id }
      .filter(_._2.uuid === gameUuid)
      .filter(_._1.fkUserId === user.id).result.headOption.map(_.isDefined)

    val game = Tables.Game.filter(_.uuid === gameUuid).result.head

    alreadyInGame.zip(game).flatMap {
      case (inGame, _) if inGame => DBIO.successful()
      case (_, game) if game.state != GameDescription.Status.ToStart.toString => DBIO.failed(new FunctionalError("Game already started"))
      case (_, game) => UserInGame += UserInGameRow(0L, game.id, user.id, None)
    }
  }

  def start(gameUuid: String, user: UserRow) = {
    val isAdmin = Tables.UserInGame.join(Tables.Game).on { (u, g) => u.fkGameId === g.id }
      .filter(_._2.uuid === gameUuid)
      .filter(_._1.fkUserId === user.id)
      .map(_._1.role === "ADMIN")
      .result.head.map(_.getOrElse(false))

    val game = Tables.Game.filter(_.uuid === gameUuid).result.head

    isAdmin.zip(game).flatMap {
      case (isAdmin, _) if !isAdmin => DBIO.failed(new FunctionalError("You cannot start the game"))
      case (_, game) if game.state != GameDescription.Status.ToStart.toString => DBIO.failed(new FunctionalError("Game already started"))
      case (_, game) => update(game.copy(state = GameDescription.Status.InPlay.toString))
    }
  }

  def markFinished(game: RichGame): DBIO[_] = {
    update(game.game.copy(state = GameDescription.Status.Finished.toString))
  }
}
