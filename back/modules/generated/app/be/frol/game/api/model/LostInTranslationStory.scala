package be.frol.game.api.model

import play.api.libs.json._

/**
  * Represents the Swagger definition for LostInTranslationStory.
  */
@javax.annotation.Generated(value = Array("org.openapitools.codegen.languages.ScalaPlayFrameworkServerCodegen"), date = "2021-02-19T18:53:26.135Z[Etc/UTC]")
case class LostInTranslationStory(
  originalUser: Option[User],
  storyId: Option[String],
  rounds: Option[List[LostInTranslationRound]]
)

object LostInTranslationStory {
  implicit lazy val lostInTranslationStoryJsonFormat: Format[LostInTranslationStory] = Json.format[LostInTranslationStory]
}

