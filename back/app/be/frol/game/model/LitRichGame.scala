package be.frol.game.model

import be.frol.game.tables.Tables.LitRoundRow
import be.frol.game.utils.TraversableUtils._

case class LitRichGame(game: RichGame, rounds : Seq[LitRoundRow]) {

  def haveToMarkGameFinished = !game.gameFinished() && finished
  def hasMoreRoundForUser(userId:Long) = rounds.count(_.fkUserId == userId) < game.users.size
  def lastRoundPlayedBy(userId:Long) = rounds.filter(_.fkUserId == userId).maxByOption(_.id)
  def user(userId : Long) = game.users.find(_.u.id == userId)
  def user(userUuid : String) = game.users.find(_.u.uuid == userUuid)
  def finished = rounds.length == (game.users.size * game.users.size)

  def nextRoundFor(userUuid:String) : Option[LitRoundRow] = {
    val userId = game.userIdFor(userUuid).get

    def userHasPlayedRound(roundToTest: LitRoundRow) = {
      !rounds.exists(round => round.fkUserId == userId && round.fkOriginalUserId == roundToTest.fkOriginalUserId)
    }

    if(!hasMoreRoundForUser(userId)){
      None
    } else if(!rounds.exists(_.fkUserId == userId)) {
      Some(LitRoundRow(0,game.id, userId, userId,None, None))
    } else {
      val userBeforeMe = Stream(sortedUsers, sortedUsers).flatten.reverse.dropWhile(_.u.id != userId).drop(1).head
      val availableRoundsForMe = rounds.filter(_.fkUserId == userBeforeMe.id)
      availableRoundsForMe
        .filterNot(userHasPlayedRound)
        .minByOption(_.id)
      }
    }
  lazy val sortedUsers = game.users.sortBy(_.uig.id)

}
