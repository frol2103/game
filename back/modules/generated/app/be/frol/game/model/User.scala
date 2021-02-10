package be.frol.game.model

import play.api.libs.json._

/**
  * Represents the Swagger definition for User.
  */
@javax.annotation.Generated(value = Array("org.openapitools.codegen.languages.ScalaPlayFrameworkServerCodegen"), date = "2021-02-10T20:48:59.917Z[Etc/UTC]")
case class User(
  uuid: Option[String],
  name: Option[String],
  canAdministrageGame: Option[Boolean],
  linkedAccounts: Option[List[UserAssociation]]
)

object User {
  implicit lazy val userJsonFormat: Format[User] = Json.format[User]
}

