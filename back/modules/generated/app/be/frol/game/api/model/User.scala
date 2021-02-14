package be.frol.game.api.model

import play.api.libs.json._

/**
  * Represents the Swagger definition for User.
  */
@javax.annotation.Generated(value = Array("org.openapitools.codegen.languages.ScalaPlayFrameworkServerCodegen"), date = "2021-02-13T21:54:18.160Z[Etc/UTC]")
case class User(
  uuid: Option[String],
  name: Option[String],
  canAdministrageGame: Option[Boolean],
  linkedAccounts: Option[List[UserAssociation]]
)

object User {
  implicit lazy val userJsonFormat: Format[User] = Json.format[User]
}

