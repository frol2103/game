package be.frol.game.mapper

import be.frol.game.api.model.{DefineItLyGame, DefineItLyResponse, DefineItLyRound}
import be.frol.game.model.{DilRichGame, DilRichResponse, DilRichRound}
import be.frol.game.utils.DateUtils._
import be.frol.game.utils.OptionUtils._

object DilMapper {

  def toDto(g: DilRichGame) : DefineItLyGame= {
    new DefineItLyGame(
      Option(GameMapper.toDto(g.game)),
      g.rounds.map(toDto(_, g)).toList.toOpt,
      GameMapper.toDto(g.points, g.game).toList.toOpt()
    )
  }

  def toDto(round: DilRichRound, g: DilRichGame): DefineItLyRound = {
    DefineItLyRound(
      UserMapper.toDto(round.userId, g.game),
      round.uuid,
      round.timestamp.map(_.toOffsetDateTime),
      round.question,
      round.responses.sortBy(_.response.uuid).map(toDto(_, g)).toList.toOpt(),
      round.status.toOpt()
    )
  }

  def toDto(r: DilRichResponse, g: DilRichGame): DefineItLyResponse = {
    DefineItLyResponse(
      r.response.uuid.toOpt(),
      r.response.response.toOpt(),
      GameMapper.toDto(r.points, g.game).headOption,
      r.response.timestamp.map(_.toOffsetDateTime),
      r.choices.sortBy(_.uuid).map(_.fkUserId).map(UserMapper.toDto(_,g.game)).flatten.toList.toOpt()
    )
  }
}
