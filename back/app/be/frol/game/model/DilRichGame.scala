package be.frol.game.model

import be.frol.game.api.model.DefineItLyRound
import be.frol.game.tables.Tables.{DilChoiceRow, DilResponseRow, DilRoundRow, _}
import play.api.Logging
import be.frol.game.utils.OptionUtils._
import be.frol.game.utils.TraversableUtils._

case class DilRichGame(game: RichGame, playedRounds: Seq[DilRichRound]) extends Logging {

  def rounds: List[DilRichRound] = nextRound.toList ::: playedRounds.toList

  def nextRound : Option[DilRichRound] = if( lastPlayedRound.map(_.status == DefineItLyRound.Status.Finished).getOrElse(true)){
    DilRichRound(game, None, Nil,Nil, game.nextPlayer(lastPlayedRound.flatMap(_.userId)).u.id.toOpt())
  }.toOpt() else None

  def round(uuid:String) = playedRounds.find(_.round.get.uuid == uuid)

  lazy val lastPlayedRound = playedRounds.maxByOption(_.id)

  def points: Seq[Score] = Nil
}

case class DilRichRound(game: RichGame, round: Option[DilRoundRow], responsesRows: Seq[DilResponseRow], choices: Seq[DilChoiceRow], newRoundUser: Option[Long] = None) {

  def id = round.map(_.id)
  def uuid = round.map(_.uuid)
  def userId = round.map(_.fkUserId).orElse(newRoundUser)
  def timestamp = round.flatMap(_.timestamp)
  def question = round.map(_.question)

  def response(uuid: String): Option[DilResponseRow] = responsesRows.find(_.uuid == uuid)

  lazy val status = {
    if(round.isEmpty) DefineItLyRound.Status.WaitQuestion
    else if (responsesRows.size != game.users.size) DefineItLyRound.Status.WaitResponses
    else if (choices.size != game.users.size - 1) DefineItLyRound.Status.WaitChoices
    else DefineItLyRound.Status.Finished
  }

  lazy val responses = {
    val choicesByResponse = choices.groupBy(_.fkResponseId)
    responsesRows.map(r => DilRichResponse(r, choicesByResponse.get(r.id).getOrElse(Nil).toList, this))
  }

  lazy val correctResponse = responses.find(_.userId == userId.get)
  def hasUserRightAnswer(userId:Long) = correctResponse.get.choices.exists(_.fkUserId == userId)


}

case class DilRichResponse(response: DilResponseRow, choices: Seq[DilChoiceRow], round:DilRichRound) {
  def userId = response.fkUserId
  lazy val userUuid = round.game.user(userId).get.uuid
  def points: Seq[Score] = Score(userUuid,"chosen",choices.size) :: Score(userUuid, "choice",(if(round.hasUserRightAnswer(userId)) 2 else 0)) :: Nil
}

object DilRichGame {
  def build(g:GameRow,users:Seq[(UserRow,UserInGameRow)], rounds : Seq[DilRoundRow], resp:Seq[DilResponseRow], choices:Seq[DilChoiceRow]): DilRichGame ={
    val respByRound = resp.groupBy(_.fkRoundId)
    val choicesByRound = choices.groupBy(_.fkRoundId)
    val game = RichGame.build(g, users.toList)
    new DilRichGame(game,rounds.map(r =>
      DilRichRound(game, r.toOpt, respByRound.get(r.id).getOrElse(Nil), choicesByRound.get(r.id).getOrElse(Nil))).toList)
  }

}
