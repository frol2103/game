package be.frol.game.mapper

import be.frol.game.model.{LitRichGame, LostInTranslationGame, LostInTranslationRound}
import be.frol.game.tables.Tables._

object LitGameMapper {

  def toLitDto(litg: LitRichGame, rounds: Option[Seq[LitRoundRow]]) = {
    LostInTranslationGame(Option(GameMapper.toDto(litg.game)), rounds.map(_.map(toDto(litg,_)).toList))
  }

  def toDto(litRichGame: LitRichGame, round: LitRoundRow) = {
    new LostInTranslationRound(
      litRichGame.user(round.fkOriginalUserId).map(UserMapper.toDto),
      litRichGame.user(round.fkUserId).map(UserMapper.toDto),
      round.text,
      round.fkFileId,
    )
  }
}
