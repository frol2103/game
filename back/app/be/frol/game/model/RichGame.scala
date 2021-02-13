package be.frol.game.model

import be.frol.game.api.model.GameDescription
import be.frol.game.tables.Tables._
import be.frol.game.utils.MapUtils
import be.frol.game.utils.TraversableUtils._

case class RichUser(u: UserRow, uig: UserInGameRow) {
  def uuid = u.uuid

  def id = u.id
}

case class UserWithReferences(user: UserRow, references: Seq[UserReferenceRow] = Nil)

case class RichGame(game: GameRow, users: List[RichUser]) {
  def id = game.id

  def userInGame(userUuid: String) = users.exists(_.uuid == userUuid)

  def user(userUuid: String) = users.find(_.uuid == userUuid)

  def user(userId: Long) = users.find(_.id == userId)

  def gameStarted() = game.state != GameDescription.Status.ToStart.toString

  def gameFinished() = game.state == GameDescription.Status.Finished.toString

  def userIdFor(userUuid: String) = users.find(_.u.uuid == userUuid).map(_.u.id)

  lazy val sortedUsers = users.sortBy(_.uig.id)

  def nextPlayer(user:Option[Long]): RichUser = {
    if(user.isEmpty) sortedUsers.head
    else Stream(sortedUsers, sortedUsers).flatten.dropWhile(_.u.id != user).drop(1).head
  }
}

object RichGame {
  def build(v: (GameRow, List[(UserInGameRow, UserRow)])) = {
    RichGame(v._1, v._2.map(u => RichUser(u._2, u._1)))
  }

  def apply(g: GameRow, users:  List[(UserRow,UserInGameRow)]) = {
    RichGame(g, users.map(u => RichUser(u._1, u._2)))
  }
}
