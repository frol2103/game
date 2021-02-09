package be.frol.game.model

import play.api.libs.json._

/**
  * Represents the Swagger definition for LostInTranslationGame.
  */
@javax.annotation.Generated(value = Array("org.openapitools.codegen.languages.ScalaPlayFrameworkServerCodegen"), date = "2021-02-09T19:22:57.725Z[Etc/UTC]")
case class LostInTranslationGame(
  game: Option[Game],
  rounds: Option[List[LostInTranslationStory]]
)

object LostInTranslationGame {
  implicit lazy val lostInTranslationGameJsonFormat: Format[LostInTranslationGame] = Json.format[LostInTranslationGame]
}

