package be.frol.game.api.controller

import org.openapitools.OpenApiExceptions
import javax.inject.{Inject, Singleton}
import play.api.libs.json._
import play.api.mvc._
import be.frol.game.api.model.DefineItLyGame
import be.frol.game.api.model.StringWrapper

@javax.annotation.Generated(value = Array("org.openapitools.codegen.languages.ScalaPlayFrameworkServerCodegen"), date = "2021-02-14T13:37:56.361Z[Etc/UTC]")
@Singleton
class DefineitlyApiController @Inject()(cc: ControllerComponents, api: DefineitlyApi) extends AbstractController(cc) {
  /**
    * POST /api/game/DefineItLy/:uuid/question
    */
  def addQuestion(uuid: String): Action[AnyContent] = Action { request =>
    def executeApi(): DefineItLyGame = {
      val text = request.body.asJson.map(_.as[StringWrapper]).getOrElse {
        throw new OpenApiExceptions.MissingRequiredParameterException("body", "text")
      }
      api.addQuestion(uuid, text)
    }

    val result = executeApi()
    val json = Json.toJson(result)
    Ok(json)
  }

  /**
    * POST /api/game/DefineItLy/:uuid/question/:questionUuid
    */
  def addResponse(uuid: String, questionUuid: String): Action[AnyContent] = Action { request =>
    def executeApi(): DefineItLyGame = {
      val text = request.body.asJson.map(_.as[StringWrapper]).getOrElse {
        throw new OpenApiExceptions.MissingRequiredParameterException("body", "text")
      }
      api.addResponse(uuid, questionUuid, text)
    }

    val result = executeApi()
    val json = Json.toJson(result)
    Ok(json)
  }

  /**
    * POST /api/game/DefineItLy/:uuid/question/:questionUuid/choice
    */
  def choseResponse(uuid: String, questionUuid: String): Action[AnyContent] = Action { request =>
    def executeApi(): DefineItLyGame = {
      val responseUuid = request.body.asJson.map(_.as[StringWrapper]).getOrElse {
        throw new OpenApiExceptions.MissingRequiredParameterException("body", "responseUuid")
      }
      api.choseResponse(uuid, questionUuid, responseUuid)
    }

    val result = executeApi()
    val json = Json.toJson(result)
    Ok(json)
  }

  /**
    * GET /api/game/DefineItLy/:uuid?withHistory=[value]
    */
  def getGame(uuid: String): Action[AnyContent] = Action { request =>
    def executeApi(): DefineItLyGame = {
      val withHistory = request.getQueryString("withHistory")
        .map(value => value.toBoolean)
      api.getGame(uuid, withHistory)
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
