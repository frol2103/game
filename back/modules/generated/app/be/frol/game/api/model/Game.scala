package be.frol.game.api.model

import play.api.libs.json._

/**
  * Represents the Swagger definition for Game.
  */
@javax.annotation.Generated(value = Array("org.openapitools.codegen.languages.ScalaPlayFrameworkServerCodegen"), date = "2021-02-12T22:33:44.691Z[Etc/UTC]")
case class Game(
  description: Option[GameDescription],
  users: Option[List[User]]
)

object Game {
  implicit lazy val gameJsonFormat: Format[Game] = Json.format[Game]
}

