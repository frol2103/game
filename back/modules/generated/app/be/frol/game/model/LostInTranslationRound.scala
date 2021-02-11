package be.frol.game.model

import play.api.libs.json._
import java.time.OffsetDateTime

/**
  * Represents the Swagger definition for LostInTranslationRound.
  * @param drawing id of the drawing file, use file api to recover it
  */
@javax.annotation.Generated(value = Array("org.openapitools.codegen.languages.ScalaPlayFrameworkServerCodegen"), date = "2021-02-11T19:33:06.677Z[Etc/UTC]")
case class LostInTranslationRound(
  roundUser: Option[User],
  storyId: Option[String],
  submissionDate: Option[OffsetDateTime],
  roundType: Option[LostInTranslationRound.RoundType.Value],
  text: Option[String],
  drawing: Option[String]
)

object LostInTranslationRound {
  implicit lazy val lostInTranslationRoundJsonFormat: Format[LostInTranslationRound] = Json.format[LostInTranslationRound]

  // noinspection TypeAnnotation
  object RoundType extends Enumeration {
    val Text = Value("text")
    val Drawing = Value("drawing")

    type RoundType = Value
    implicit lazy val RoundTypeJsonFormat: Format[Value] = Format(Reads.enumNameReads(this), Writes.enumNameWrites[this.type])
  }
}

