package be.frol.game.api.model

import play.api.libs.json._
import java.time.OffsetDateTime

/**
  * Represents the Swagger definition for DefineItLyResponse.
  */
@javax.annotation.Generated(value = Array("org.openapitools.codegen.languages.ScalaPlayFrameworkServerCodegen"), date = "2021-02-14T14:44:44.961Z[Etc/UTC]")
case class DefineItLyResponse(
  uuid: Option[String],
  response: Option[String],
  responseByUser: Option[UserWithScore],
  submissionDate: Option[OffsetDateTime],
  chosenBy: Option[List[User]]
)

object DefineItLyResponse {
  implicit lazy val defineItLyResponseJsonFormat: Format[DefineItLyResponse] = Json.format[DefineItLyResponse]
}

