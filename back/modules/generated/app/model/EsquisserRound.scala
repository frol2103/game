package model

import play.api.libs.json._

/**
  * Represents the Swagger definition for EsquisserRound.
  * @param drawing url of photo
  */
@javax.annotation.Generated(value = Array("org.openapitools.codegen.languages.ScalaPlayFrameworkServerCodegen"), date = "2021-02-06T08:42:09.258Z[Etc/UTC]")
case class EsquisserRound(
  originalUser: Option[User],
  roundUser: Option[User],
  text: Option[String],
  drawing: Option[String]
)

object EsquisserRound {
  implicit lazy val esquisserRoundJsonFormat: Format[EsquisserRound] = Json.format[EsquisserRound]
}

