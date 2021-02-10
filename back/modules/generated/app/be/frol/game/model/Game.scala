package be.frol.game.model

import play.api.libs.json._

/**
  * Represents the Swagger definition for Game.
  */
@javax.annotation.Generated(value = Array("org.openapitools.codegen.languages.ScalaPlayFrameworkServerCodegen"), date = "2021-02-10T16:24:51.935Z[Etc/UTC]")
case class Game(
  description: Option[GameDescription],
  users: Option[List[User]]
)

object Game {
  implicit lazy val gameJsonFormat: Format[Game] = Json.format[Game]
}

