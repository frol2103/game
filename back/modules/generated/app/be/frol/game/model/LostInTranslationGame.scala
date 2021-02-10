package be.frol.game.model

import play.api.libs.json._

/**
  * Represents the Swagger definition for LostInTranslationGame.
  */
@javax.annotation.Generated(value = Array("org.openapitools.codegen.languages.ScalaPlayFrameworkServerCodegen"), date = "2021-02-10T22:47:12.422Z[Etc/UTC]")
case class LostInTranslationGame(
  game: Option[Game],
  stories: Option[List[LostInTranslationStory]]
)

object LostInTranslationGame {
  implicit lazy val lostInTranslationGameJsonFormat: Format[LostInTranslationGame] = Json.format[LostInTranslationGame]
}

