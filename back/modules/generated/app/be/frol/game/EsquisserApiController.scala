package be.frol.game

import org.openapitools.OpenApiExceptions
import javax.inject.{Inject, Singleton}
import play.api.libs.json._
import play.api.mvc._
import model.EsquisserGame

@javax.annotation.Generated(value = Array("org.openapitools.codegen.languages.ScalaPlayFrameworkServerCodegen"), date = "2021-02-06T08:42:09.258Z[Etc/UTC]")
@Singleton
class EsquisserApiController @Inject()(cc: ControllerComponents, api: EsquisserApi) extends AbstractController(cc) {
  /**
    * POST /api/game/esquisse/:uuid/drawing
    */
  def addDrawingRound(uuid: String): Action[AnyContent] = Action { request =>
    def executeApi(): List[EsquisserGame] = {
      val text = request.body.asJson.map(_.as[String]).getOrElse {
        throw new OpenApiExceptions.MissingRequiredParameterException("body", "text")
      }
      api.addDrawingRound(uuid, text)
    }

    val result = executeApi()
    val json = Json.toJson(result)
    Ok(json)
  }

  /**
    * POST /api/game/esquisse/:uuid/text
    */
  def addTextRound(uuid: String): Action[AnyContent] = Action { request =>
    def executeApi(): List[EsquisserGame] = {
      val text = request.body.asJson.map(_.as[String]).getOrElse {
        throw new OpenApiExceptions.MissingRequiredParameterException("body", "text")
      }
      api.addTextRound(uuid, text)
    }

    val result = executeApi()
    val json = Json.toJson(result)
    Ok(json)
  }

  /**
    * GET /api/game/esquisse/:uuid
    */
  def getGame(uuid: String): Action[AnyContent] = Action { request =>
    def executeApi(): List[EsquisserGame] = {
      api.getGame(uuid)
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
