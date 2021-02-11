package be.frol.game.model

import play.api.libs.json._

/**
  * Represents the Swagger definition for UserWithPoints.
  */
@javax.annotation.Generated(value = Array("org.openapitools.codegen.languages.ScalaPlayFrameworkServerCodegen"), date = "2021-02-11T20:52:08.970Z[Etc/UTC]")
case class UserWithPoints(
  user: Option[User],
  points: Option[List[Points]]
)

object UserWithPoints {
  implicit lazy val userWithPointsJsonFormat: Format[UserWithPoints] = Json.format[UserWithPoints]
}

