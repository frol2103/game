package model

import play.api.libs.json._

/**
  * Represents the Swagger definition for AuthUser.
  */
@javax.annotation.Generated(value = Array("org.openapitools.codegen.languages.ScalaPlayFrameworkServerCodegen"), date = "2021-02-06T08:42:09.258Z[Etc/UTC]")
case class AuthUser(
  name: Option[String],
  reference: Option[String]
)

object AuthUser {
  implicit lazy val authUserJsonFormat: Format[AuthUser] = Json.format[AuthUser]
}

