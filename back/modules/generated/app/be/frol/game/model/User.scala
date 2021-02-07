package be.frol.game.model

import play.api.libs.json._

/**
  * Represents the Swagger definition for User.
  */
@javax.annotation.Generated(value = Array("org.openapitools.codegen.languages.ScalaPlayFrameworkServerCodegen"), date = "2021-02-07T13:39:39.939Z[Etc/UTC]")
case class User(
  id: Option[Long],
  name: Option[String],
  canAdministrageGame: Option[Boolean]
)

object User {
  implicit lazy val userJsonFormat: Format[User] = Json.format[User]
}

