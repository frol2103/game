package model

import play.api.libs.json._

/**
  * Represents the Swagger definition for AuthUser.
  */
@javax.annotation.Generated(value = Array("org.openapitools.codegen.languages.ScalaPlayFrameworkServerCodegen"), date = "2021-02-06T08:46:12.954Z[Etc/UTC]")
case class AuthUser(
  name: Option[String],
  reference: Option[String]
)

object AuthUser {
  implicit lazy val authUserJsonFormat: Format[AuthUser] = Json.format[AuthUser]
}

