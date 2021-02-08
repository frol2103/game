package be.frol.game.api

import org.openapitools.OpenApiExceptions
import javax.inject.{Inject, Singleton}
import play.api.libs.json._
import play.api.mvc._

@javax.annotation.Generated(value = Array("org.openapitools.codegen.languages.ScalaPlayFrameworkServerCodegen"), date = "2021-02-08T18:07:22.741Z[Etc/UTC]")
@Singleton
class FileApiController @Inject()(cc: ControllerComponents, api: FileApi) extends AbstractController(cc) {
  /**
    * GET /api/file/:id
    */
  def getFile(id: String): Action[AnyContent] = Action { request =>
    def executeApi(): Unit = {
      api.getFile(id)
    }

    executeApi()
    Ok
  }

  private def splitCollectionParam(paramValues: String, collectionFormat: String): List[String] = {
    val splitBy =
      collectionFormat match {
        case "csv" => ",+"
        case "tsv" => "\t+"
        case "ssv" => " +"
        case "pipes" => "|+"
      }

    paramValues.split(splitBy).toList
  }
}
