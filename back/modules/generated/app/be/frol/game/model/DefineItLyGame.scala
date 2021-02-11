package be.frol.game.model

import play.api.libs.json._

/**
  * Represents the Swagger definition for DefineItLyGame.
  */
@javax.annotation.Generated(value = Array("org.openapitools.codegen.languages.ScalaPlayFrameworkServerCodegen"), date = "2021-02-11T20:52:08.970Z[Etc/UTC]")
case class DefineItLyGame(
  game: Option[Game],
  rounds: Option[List[DefineItLyRound]],
  points: Option[List[UserWithPoints]]
)

object DefineItLyGame {
  implicit lazy val defineItLyGameJsonFormat: Format[DefineItLyGame] = Json.format[DefineItLyGame]
}

