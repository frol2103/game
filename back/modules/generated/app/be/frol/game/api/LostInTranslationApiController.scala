package be.frol.game.api

import org.openapitools.OpenApiExceptions
import javax.inject.{Inject, Singleton}
import play.api.libs.json._
import play.api.mvc._
import be.frol.game.model.LostInTranslationGame
import play.api.libs.Files.TemporaryFile

@javax.annotation.Generated(value = Array("org.openapitools.codegen.languages.ScalaPlayFrameworkServerCodegen"), date = "2021-02-10T16:24:51.935Z[Etc/UTC]")
@Singleton
class LostInTranslationApiController @Inject()(cc: ControllerComponents, api: LostInTranslationApi) extends AbstractController(cc) {
  /**
    * POST /api/game/LostInTranslation/:uuid/story/:storyId/drawing
    */
  def addDrawingRound(uuid: String, storyId: String): Action[AnyContent] = Action { request =>
    def executeApi(): LostInTranslationGame = {
      val file = request.body.asMultipartFormData.flatMap(_.file("file").map(_.ref: TemporaryFile))
        .getOrElse {
          throw new OpenApiExceptions.MissingRequiredParameterException("file", "form")
        }
      api.addDrawingRound(uuid, storyId, file)
    }

    val result = executeApi()
    val json = Json.toJson(result)
    Ok(json)
  }

  /**
    * POST /api/game/LostInTranslation/:uuid/story/:storyId/text
    */
  def addTextRound(uuid: String, storyId: String): Action[AnyContent] = Action { request =>
    def executeApi(): LostInTranslationGame = {
      val text = request.body.asJson.map(_.as[String]).getOrElse {
        throw new OpenApiExceptions.MissingRequiredParameterException("body", "text")
      }
      api.addTextRound(uuid, storyId, text)
    }

    val result = executeApi()
    val json = Json.toJson(result)
    Ok(json)
  }

  /**
    * GET /api/game/LostInTranslation/:uuid
    */
  def getGame(uuid: String): Action[AnyContent] = Action { request =>
    def executeApi(): LostInTranslationGame = {
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
