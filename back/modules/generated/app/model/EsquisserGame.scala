package model

import play.api.libs.json._

/**
  * Represents the Swagger definition for EsquisserGame.
  */
@javax.annotation.Generated(value = Array("org.openapitools.codegen.languages.ScalaPlayFrameworkServerCodegen"), date = "2021-02-06T08:27:19.178Z[Etc/UTC]")
case class EsquisserGame(
  game: Option[Game],
  rounds: Option[List[EsquisserRound]]
)

object EsquisserGame {
  implicit lazy val esquisserGameJsonFormat: Format[EsquisserGame] = Json.format[EsquisserGame]
}

