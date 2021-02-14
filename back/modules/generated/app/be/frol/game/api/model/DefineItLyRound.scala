package be.frol.game.api.model

import play.api.libs.json._
import java.time.OffsetDateTime

/**
  * Represents the Swagger definition for DefineItLyRound.
  */
@javax.annotation.Generated(value = Array("org.openapitools.codegen.languages.ScalaPlayFrameworkServerCodegen"), date = "2021-02-14T13:37:56.361Z[Etc/UTC]")
case class DefineItLyRound(
  quedtionByUser: Option[User],
  roundId: Option[String],
  submissionDate: Option[OffsetDateTime],
  question: Option[String],
  responses: Option[List[DefineItLyResponse]],
  status: Option[DefineItLyRound.Status.Value]
)

object DefineItLyRound {
  implicit lazy val defineItLyRoundJsonFormat: Format[DefineItLyRound] = Json.format[DefineItLyRound]

  // noinspection TypeAnnotation
  object Status extends Enumeration {
    val WaitQuestion = Value("waitQuestion")
    val WaitResponses = Value("waitResponses")
    val WaitChoices = Value("waitChoices")
    val Finished = Value("finished")

    type Status = Value
    implicit lazy val StatusJsonFormat: Format[Value] = Format(Reads.enumNameReads(this), Writes.enumNameWrites[this.type])
  }
}

