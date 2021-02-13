package be.frol.game.api.model

import play.api.libs.json._

/**
  * Represents the Swagger definition for Score.
  */
@javax.annotation.Generated(value = Array("org.openapitools.codegen.languages.ScalaPlayFrameworkServerCodegen"), date = "2021-02-12T22:33:44.691Z[Etc/UTC]")
case class Score(
  amount: Option[BigDecimal],
  category: Option[String]
)

object Score {
  implicit lazy val scoreJsonFormat: Format[Score] = Json.format[Score]
}

