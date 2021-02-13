package be.frol.game.model

import be.frol.game.api.model.DefineItLyRound
import be.frol.game.tables.Tables.{DilChoiceRow, DilResponseRow, DilRoundRow, _}
import play.api.Logging
import be.frol.game.utils.OptionUtils._
import be.frol.game.utils.TraversableUtils._

case class DilRichGame(game: RichGame, playedRounds: Seq[DilRichRound]) extends Logging {

  def rounds: List[DilRichRound] = nextRound.toList ::: playedRounds.toList

  def nextRound : Option[DilRichRound] = if(lastPlayedRound.map(_.status == DefineItLyRound.Status.Finished).getOrElse(true)){
    DilRichRound(game, None, Nil,Nil,nextUserForQuestion.toOpt, game.nextPlayer(lastPlayedRound.flatMap(_.userId)))
  } else None


  def nextUserForQuestion : Long = if(playedRounds.isEmpty) game.sortedUsers

  lazy var lastPlayedRound = playedRounds.maxByOption(_.id)

  def points: Seq[Score] = Nil
}

case class DilRichRound(game: RichGame, round: Option[DilRoundRow], responsesRows: Seq[DilResponseRow], choices: Seq[DilChoiceRow], newRoundUser: Option[Long] = None) {
  def id = round.map(_.uuid)
  def userId = round.map(_.fkUserId).orElse(newRoundUser)
  def timestamp = round.flatMap(_.timestamp)
  def question = round.map(_.question)

  lazy val status = {
    if (responsesRows.size != game.users.size) DefineItLyRound.Status.WaitResponses
    else if (choices.size != game.users.size - 1) DefineItLyRound.Status.WaitChoices
    else DefineItLyRound.Status.Finished
  }

  lazy val responses = {
    val choicesByResponse = choices.groupBy(_.fkResponseId)
    responsesRows.map(r => DilRichResponse(r, choicesByResponse.get(r.id).getOrElse(Nil).toList, game))
  }


}

case class DilRichResponse(response: DilResponseRow, choices: Seq[DilChoiceRow], game: DilRichGame) {
  def points: Seq[Score] = Nil
}

object DilRichGame {
  def build(g:GameRow,users:Seq[(UserRow,UserInGameRow)], rounds : Seq[DilRoundRow], resp:Seq[DilResponseRow], choices:Seq[DilChoiceRow]): DilRichGame ={
    val respByRound = resp.groupBy(_.fkRoundId)
    val choicesByRound = choices.groupBy(_.fkRoundId)
    val game = RichGame(g, users.toList)
    new DilRichGame(game,rounds.map(r =>
      DilRichRound(game, r.toOpt, respByRound.get(r.id).getOrElse(Nil), choicesByRound.get(r.id).getOrElse(Nil))).toList)
  }

}
