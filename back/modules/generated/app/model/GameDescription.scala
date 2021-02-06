package model

import play.api.libs.json._
import java.time.OffsetDateTime

/**
  * Represents the Swagger definition for GameDescription.
  */
@javax.annotation.Generated(value = Array("org.openapitools.codegen.languages.ScalaPlayFrameworkServerCodegen"), date = "2021-02-06T08:42:09.258Z[Etc/UTC]")
case class GameDescription(
  id: Option[Long],
  uuid: Option[String],
  startTime: Option[OffsetDateTime],
  status: Option[GameDescription.Status.Value],
  gameType: Option[GameDescription.GameType.Value]
)

object GameDescription {
  implicit lazy val gameDescriptionJsonFormat: Format[GameDescription] = Json.format[GameDescription]

  // noinspection TypeAnnotation
  object Status extends Enumeration {
    val ToStart = Value("toStart")
    val InPlay = Value("inPlay")
    val Finished = Value("finished")

    type Status = Value
    implicit lazy val StatusJsonFormat: Format[Value] = Format(Reads.enumNameReads(this), Writes.enumNameWrites[this.type])
  }

  // noinspection TypeAnnotation
  object GameType extends Enumeration {
    val Esquisser = Value("esquisser")

    type GameType = Value
    implicit lazy val GameTypeJsonFormat: Format[Value] = Format(Reads.enumNameReads(this), Writes.enumNameWrites[this.type])
  }
}

