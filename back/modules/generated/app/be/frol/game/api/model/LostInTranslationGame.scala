package be.frol.game.api.model

import play.api.libs.json._

/**
  * Represents the Swagger definition for LostInTranslationGame.
  */
@javax.annotation.Generated(value = Array("org.openapitools.codegen.languages.ScalaPlayFrameworkServerCodegen"), date = "2021-02-14T14:36:31.590Z[Etc/UTC]")
case class LostInTranslationGame(
  game: Option[Game],
  stories: Option[List[LostInTranslationStory]]
)

object LostInTranslationGame {
  implicit lazy val lostInTranslationGameJsonFormat: Format[LostInTranslationGame] = Json.format[LostInTranslationGame]
}

