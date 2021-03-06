package be.frol.game.mapper

import be.frol.game.api.model.{LostInTranslationGame, LostInTranslationRound, LostInTranslationStory}
import be.frol.game.model.{LitRichGame, LitRichRound}
import be.frol.game.utils.DateUtils._

object LitGameMapper {

  def toLitDto(litg: LitRichGame, roundTransform: LitRichRound => LitRichRound = (v => v)): LostInTranslationGame = {
    LostInTranslationGame(Option(GameMapper.toDto(litg.game)),
      Option(litg.rounds.groupBy(_.storyId).map { case (k, v) => toDto(litg, k, v, roundTransform) }.toList)
    )
  }

  def toDto(litRichGame: LitRichGame, storyId: String, rounds: Iterable[LitRichRound], roundTransform: LitRichRound => LitRichRound): LostInTranslationStory = {
    new LostInTranslationStory(
      rounds.headOption.map(_.round.fkOriginalUserId).flatMap(litRichGame.user).map(UserMapper.toDto),
      Option(storyId),
      Option(rounds.toList.sortBy(_.round.timestamp.map(_.getTime).getOrElse(Long.MaxValue))
        .map(roundTransform).map(toDto(litRichGame, _))))
  }

  def toDto(litRichGame: LitRichGame, round: LitRichRound): LostInTranslationRound = {
    new LostInTranslationRound(
      litRichGame.user(round.round.fkUserId).map(UserMapper.toDto),
      Option(round.storyId),
      round.round.timestamp.map(_.toOffsetDateTime),
      round.roundType,
      round.round.text,
      round.round.fkFileId,
    )
  }
}
