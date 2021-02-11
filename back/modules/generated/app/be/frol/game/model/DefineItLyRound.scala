package be.frol.game.model

import play.api.libs.json._
import java.time.OffsetDateTime

/**
  * Represents the Swagger definition for DefineItLyRound.
  */
@javax.annotation.Generated(value = Array("org.openapitools.codegen.languages.ScalaPlayFrameworkServerCodegen"), date = "2021-02-11T20:52:08.970Z[Etc/UTC]")
case class DefineItLyRound(
  uuid: Option[String],
  quedtionByUser: Option[User],
  roundId: Option[String],
  submissionDate: Option[OffsetDateTime],
  question: Option[String],
  responses: Option[DefineItLyResponse],
  status: Option[DefineItLyRound.Status.Value]
)

object DefineItLyRound {
  implicit lazy val defineItLyRoundJsonFormat: Format[DefineItLyRound] = Json.format[DefineItLyRound]

  // noinspection TypeAnnotation
  object Status extends Enumeration {
    val WaitQuestion = Value("waitQuestion")
    val WaitResponses = Value("waitResponses")
    val ChoseResponses = Value("choseResponses")
    val Finished = Value("finished")

    type Status = Value
    implicit lazy val StatusJsonFormat: Format[Value] = Format(Reads.enumNameReads(this), Writes.enumNameWrites[this.type])
  }
}

