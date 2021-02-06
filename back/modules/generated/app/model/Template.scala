package model

import play.api.libs.json._

/**
  * Represents the Swagger definition for Template.
  */
@javax.annotation.Generated(value = Array("org.openapitools.codegen.languages.ScalaPlayFrameworkServerCodegen"), date = "2021-02-06T08:05:12.524Z[Etc/UTC]")
case class Template(
  uuid: Option[String],
  parent: Option[List[Template]],
  content: Option[List[FieldContent]]
)

object Template {
  implicit lazy val templateJsonFormat: Format[Template] = Json.format[Template]
}

