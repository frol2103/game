package be.frol.game.api.model

import play.api.libs.json._
import java.time.OffsetDateTime

/**
  * Represents the Swagger definition for DefineItLyResponse.
  */
@javax.annotation.Generated(value = Array("org.openapitools.codegen.languages.ScalaPlayFrameworkServerCodegen"), date = "2021-02-12T22:33:44.691Z[Etc/UTC]")
case class DefineItLyResponse(
  uuid: Option[String],
  response: Option[String],
  responseByUser: Option[User],
  submissionDate: Option[OffsetDateTime],
  chosenBy: Option[List[UserWithScore]]
)

object DefineItLyResponse {
  implicit lazy val defineItLyResponseJsonFormat: Format[DefineItLyResponse] = Json.format[DefineItLyResponse]
}

