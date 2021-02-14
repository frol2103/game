package be.frol.game.api.model

import play.api.libs.json._

/**
  * Represents the Swagger definition for UserAssociation.
  */
@javax.annotation.Generated(value = Array("org.openapitools.codegen.languages.ScalaPlayFrameworkServerCodegen"), date = "2021-02-13T21:54:18.160Z[Etc/UTC]")
case class UserAssociation(
  linkType: Option[UserAssociation.LinkType.Value],
  accessToken: Option[String]
)

object UserAssociation {
  implicit lazy val userAssociationJsonFormat: Format[UserAssociation] = Json.format[UserAssociation]

  // noinspection TypeAnnotation
  object LinkType extends Enumeration {
    val Facebook = Value("Facebook")

    type LinkType = Value
    implicit lazy val LinkTypeJsonFormat: Format[Value] = Format(Reads.enumNameReads(this), Writes.enumNameWrites[this.type])
  }
}

