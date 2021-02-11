package be.frol.game.model

import play.api.libs.json._

/**
  * Represents the Swagger definition for Points.
  */
@javax.annotation.Generated(value = Array("org.openapitools.codegen.languages.ScalaPlayFrameworkServerCodegen"), date = "2021-02-11T20:52:08.970Z[Etc/UTC]")
case class Points(
  amount: Option[BigDecimal],
  category: Option[String]
)

object Points {
  implicit lazy val pointsJsonFormat: Format[Points] = Json.format[Points]
}

