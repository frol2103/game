package be.frol.game.mapper

import be.frol.game.api.model.{Game, GameDescription, User, UserWithScore}
import be.frol.game.model.{RichGame, Score}
import be.frol.game.tables.Tables
import be.frol.game.utils.DateUtils._
import be.frol.game.utils.OptionUtils._

object GameMapper {
  def toDto(g: (Tables.GameRow, List[(Tables.UserInGameRow, Tables.UserRow)])): Game = {
    Game(Option(toDto(g._1)), Option(toDto(g._2)))
  }

  def toDto(users: List[(Tables.UserInGameRow, Tables.UserRow)]): List[User] = {
    users.map { case (uInGame, u) =>
      User(Option(u.uuid), Option(u.name), Option(uInGame.role.map(_ == "ADMIN").getOrElse(false)), None)
    }
  }

  def toDto(g: Tables.GameRow): GameDescription = {
    GameDescription(
      Option(g.id),
      Option(g.uuid),
      Option(g.timestamp.toOffsetDateTime),
      Option(GameDescription.Status.withName(g.state)),
      Option(GameDescription.GameType.withName(g.gameType)))
  }

  def toDto(g: RichGame): Game = toDto(g.game -> g.users.map(u => u.uig -> u.u))

  def toDto(scores: Seq[Score], game: RichGame) =
    scores
      .groupBy(_.userUuid)
      .mapValues(_.map(s => be.frol.game.api.model.Score(BigDecimal(s.amount).toOpt,s.category.toOpt())))
      .map { case (k, v) => UserWithScore(game.user(k).map(UserMapper.toDto), v.toList.toOpt()) }

}
