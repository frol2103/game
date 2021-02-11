package be.frol.game.model

import play.api.libs.json._

/**
  * Represents the Swagger definition for DefineItLyResponse.
  */
@javax.annotation.Generated(value = Array("org.openapitools.codegen.languages.ScalaPlayFrameworkServerCodegen"), date = "2021-02-11T20:52:08.970Z[Etc/UTC]")
case class DefineItLyResponse(
  uuid: Option[String],
  response: Option[String],
  responseByUser: Option[User],
  chosenBy: Option[List[UserWithPoints]]
)

object DefineItLyResponse {
  implicit lazy val defineItLyResponseJsonFormat: Format[DefineItLyResponse] = Json.format[DefineItLyResponse]
}

