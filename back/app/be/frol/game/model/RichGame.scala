package be.frol.game.model
import be.frol.game.error.FunctionalError
import be.frol.game.tables.Tables._

case class RichUser(u:UserRow, uig: UserInGameRow){
  def uuid = u.uuid
  def id = u.id
}

case class RichGame(game: GameRow, users: List[RichUser]){
  def id = game.id
  def userInGame(userUuid:String) = users.exists(_.uuid == userUuid)
  def gameStarted() = game.state != GameDescription.Status.ToStart.toString
  def gameFinished() = game.state == GameDescription.Status.Finished.toString

  def userIdFor(userUuid : String) = users.find(_.u.uuid==userUuid).map(_.u.id)
}

object RichGame{
  def build(v : (GameRow, List[(UserInGameRow, UserRow)])) = {
    RichGame(v._1,v._2.map(u => RichUser(u._2, u._1)))
  }
}
