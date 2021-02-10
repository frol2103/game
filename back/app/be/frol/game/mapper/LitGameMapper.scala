package be.frol.game.mapper

import be.frol.game.model.{LitRichGame, LitRichRound, LostInTranslationGame, LostInTranslationRound, LostInTranslationStory}
import be.frol.game.tables.Tables._
import be.frol.game.utils.TraversableUtils._
import be.frol.game.utils.DateUtils._

object LitGameMapper {

  def toLitDto(litg: LitRichGame, roundTransform: LitRichRound => LitRichRound = (v => v)) : LostInTranslationGame = {
    LostInTranslationGame(Option(GameMapper.toDto(litg.game)),
      Option(litg.rounds.groupBy(_.storyId).map{case(k,v) => toDto(litg,k,v, roundTransform)}.toList)
    )
  }

  def toDto(litRichGame: LitRichGame, storyId:String, rounds: Iterable[LitRichRound], roundTransform: LitRichRound => LitRichRound) : LostInTranslationStory = {
    new LostInTranslationStory(
      rounds.headOption.map(_.round.fkOriginalUserId).flatMap(litRichGame.user).map(UserMapper.toDto),
      Option(storyId),
      Option(rounds.map(roundTransform).map(toDto(litRichGame, _)).toList))
  }

  def toDto(litRichGame: LitRichGame, round: LitRichRound) : LostInTranslationRound = {
    new LostInTranslationRound(
      litRichGame.user(round.round.fkOriginalUserId).map(UserMapper.toDto),
      litRichGame.user(round.round.fkUserId).map(UserMapper.toDto),
      Option(round.storyId),
      round.round.timestamp.map(_.toOffsetDateTime),
      round.roundType,
      round.round.text,
      round.round.fkFileId,
    )
  }
}
