package model

import play.api.libs.json._

/**
  * Represents the Swagger definition for LostInTranslationRound.
  * @param drawing url of photo
  */
@javax.annotation.Generated(value = Array("org.openapitools.codegen.languages.ScalaPlayFrameworkServerCodegen"), date = "2021-02-06T08:46:12.954Z[Etc/UTC]")
case class LostInTranslationRound(
  originalUser: Option[User],
  roundUser: Option[User],
  text: Option[String],
  drawing: Option[String]
)

object LostInTranslationRound {
  implicit lazy val lostInTranslationRoundJsonFormat: Format[LostInTranslationRound] = Json.format[LostInTranslationRound]
}

