package be.frol.game.api.model

import play.api.libs.json._

/**
  * Represents the Swagger definition for StringWrapper.
  */
@javax.annotation.Generated(value = Array("org.openapitools.codegen.languages.ScalaPlayFrameworkServerCodegen"), date = "2021-02-14T14:36:31.590Z[Etc/UTC]")
case class StringWrapper(
  value: String
)

object StringWrapper {
  implicit lazy val stringWrapperJsonFormat: Format[StringWrapper] = Json.format[StringWrapper]
}

