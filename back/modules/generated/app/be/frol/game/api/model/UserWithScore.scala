package be.frol.game.api.model

import play.api.libs.json._

/**
  * Represents the Swagger definition for UserWithScore.
  */
@javax.annotation.Generated(value = Array("org.openapitools.codegen.languages.ScalaPlayFrameworkServerCodegen"), date = "2021-02-19T18:53:26.135Z[Etc/UTC]")
case class UserWithScore(
  user: Option[User],
  points: Option[List[Score]]
)

object UserWithScore {
  implicit lazy val userWithScoreJsonFormat: Format[UserWithScore] = Json.format[UserWithScore]
}

