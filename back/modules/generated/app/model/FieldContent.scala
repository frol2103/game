package model

import play.api.libs.json._
import java.util.UUID

/**
  * Represents the Swagger definition for FieldContent.
  * @param value value of the type
  */
@javax.annotation.Generated(value = Array("org.openapitools.codegen.languages.ScalaPlayFrameworkServerCodegen"), date = "2021-02-06T08:05:12.524Z[Etc/UTC]")
case class FieldContent(
  fieldUuid: Option[String],
  value: Option[JsObject],
  valueOrigin: Option[UUID]
)

object FieldContent {
  implicit lazy val fieldContentJsonFormat: Format[FieldContent] = Json.format[FieldContent]
}

