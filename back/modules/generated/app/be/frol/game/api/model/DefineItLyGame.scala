package be.frol.game.api.model

import play.api.libs.json._

/**
  * Represents the Swagger definition for DefineItLyGame.
  */
@javax.annotation.Generated(value = Array("org.openapitools.codegen.languages.ScalaPlayFrameworkServerCodegen"), date = "2021-02-14T14:44:44.961Z[Etc/UTC]")
case class DefineItLyGame(
  game: Option[Game],
  rounds: Option[List[DefineItLyRound]],
  points: Option[List[UserWithScore]]
)

object DefineItLyGame {
  implicit lazy val defineItLyGameJsonFormat: Format[DefineItLyGame] = Json.format[DefineItLyGame]
}

