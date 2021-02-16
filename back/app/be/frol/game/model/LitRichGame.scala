package be.frol.game.model

import be.frol.game.api.model.LostInTranslationRound
import be.frol.game.api.model.LostInTranslationRound.RoundType.RoundType
import be.frol.game.tables.Tables.LitRoundRow
import be.frol.game.utils.OptionUtils._
import be.frol.game.utils.TraversableUtils._
import play.api.Logging

case class LitRichGame(game: RichGame, playedRounds: Seq[LitRoundRow]) extends Logging {

  def haveToMarkGameFinished = !game.gameFinished() && finished

  def hasMoreRoundForUser(userId: Long) = playedRounds.count(_.fkUserId == userId) < game.users.size

  def lastRoundPlayedBy(userId: Long) = playedRounds.filter(_.fkUserId == userId).maxByOption(_.id)

  def user(userId: Long) = game.users.find(_.u.id == userId)

  def user(userUuid: String) = game.users.find(_.u.uuid == userUuid)

  def finished = playedRounds.length == (game.users.size * game.users.size)

  def rounds(): Iterable[LitRichRound] = playedRichRounds.toList ::: additionalRounds

  lazy val playedRichRounds = playedRounds.map(LitRichRound.auto)

  def missingInitialRounds() = {
    game.users.filterNot(u => playedRounds.exists(_.fkOriginalUserId == u.id))
      .map(u => new LitRoundRow(
        0L,
        game.id,
        u.id,
        u.id,
        None,
        None,
        None,
        u.uuid
      )).map(LitRichRound(_, LostInTranslationRound.RoundType.Text.toOpt))
  }

  lazy val additionalRounds = (missingInitialRounds() ::: roundsToPlay().toList)

  def nextRoundsFor(userId: Long): List[LitRichRound] = additionalRounds.filter(_.round.fkUserId == userId)

  def nextRoundsFor(userUuid: String): List[LitRichRound] = nextRoundsFor(user(userUuid).get.id)

  def roundsToPlay() = {
    playedRichRounds.groupBy(_.storyId).map(_._2.maxBy(_.round.id)).map { richRound =>
      LitRichRound(
        richRound.round.copy(
          id = 0L,
          fkUserId = richRound.nextPlayer(this).u.id,
          timestamp = None,
          text = None,
          fkFileId = None,
        ),
        Option(alternate(richRound.roundType.get)))
    }
  }

  def alternate(roundType: RoundType) =
    if (roundType == LostInTranslationRound.RoundType.Text) LostInTranslationRound.RoundType.Drawing else LostInTranslationRound.RoundType.Text


}

case class LitRichRound(round: LitRoundRow, roundType: Option[LostInTranslationRound.RoundType.Value]) {
  def storyId = round.storyId

  def nextPlayer(litg: LitRichGame): RichUser = {
    litg.game.nextPlayer(round.fkUserId.toOpt())
  }
}

object LitRichRound {
  def auto(row: LitRoundRow) = LitRichRound(row, (row.text -> row.fkFileId) match {
    case (None, None) => None
    case (Some(_), None) => Some(LostInTranslationRound.RoundType.Text)
    case (None, Some(_)) => Some(LostInTranslationRound.RoundType.Drawing)
  })
}
