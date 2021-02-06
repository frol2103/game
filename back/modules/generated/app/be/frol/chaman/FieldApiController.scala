package be.frol.chaman

import org.openapitools.OpenApiExceptions
import javax.inject.{Inject, Singleton}
import play.api.libs.json._
import play.api.mvc._
import model.Field

@javax.annotation.Generated(value = Array("org.openapitools.codegen.languages.ScalaPlayFrameworkServerCodegen"), date = "2021-02-06T08:05:12.524Z[Etc/UTC]")
@Singleton
class FieldApiController @Inject()(cc: ControllerComponents, api: FieldApi) extends AbstractController(cc) {
  /**
    * GET /api/field
    */
  def getField(): Action[AnyContent] = Action { request =>
    def executeApi(): Field = {
      api.getField()
    }

    val result = executeApi()
    val json = Json.toJson(result)
    Ok(json)
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
