package model

import play.api.libs.json._

/**
  * Represents the Swagger definition for User.
  */
@javax.annotation.Generated(value = Array("org.openapitools.codegen.languages.ScalaPlayFrameworkServerCodegen"), date = "2021-02-06T08:46:12.954Z[Etc/UTC]")
case class User(
  id: Option[Long],
  name: Option[String]
)

object User {
  implicit lazy val userJsonFormat: Format[User] = Json.format[User]
}

