package be.frol.game.model

import play.api.libs.json._

/**
  * Represents the Swagger definition for LostInTranslationGame.
  */
@javax.annotation.Generated(value = Array("org.openapitools.codegen.languages.ScalaPlayFrameworkServerCodegen"), date = "2021-02-06T22:43:22.950Z[Etc/UTC]")
case class LostInTranslationGame(
  game: Option[Game],
  rounds: Option[List[LostInTranslationRound]]
)

object LostInTranslationGame {
  implicit lazy val lostInTranslationGameJsonFormat: Format[LostInTranslationGame] = Json.format[LostInTranslationGame]
}

