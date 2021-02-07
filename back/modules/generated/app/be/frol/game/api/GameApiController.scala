package be.frol.game.api

import org.openapitools.OpenApiExceptions
import javax.inject.{Inject, Singleton}
import play.api.libs.json._
import play.api.mvc._
import be.frol.game.model.Game
import be.frol.game.model.GameDescription

@javax.annotation.Generated(value = Array("org.openapitools.codegen.languages.ScalaPlayFrameworkServerCodegen"), date = "2021-02-07T10:16:16.685Z[Etc/UTC]")
@Singleton
class GameApiController @Inject()(cc: ControllerComponents, api: GameApi) extends AbstractController(cc) {
  /**
    * POST /api/game/create
    */
  def createAGame(): Action[AnyContent] = Action { request =>
    def executeApi(): Game = {
      val game = request.body.asJson.map(_.as[GameDescription]).getOrElse {
        throw new OpenApiExceptions.MissingRequiredParameterException("body", "game")
      }
      api.createAGame(game)
    }

    val result = executeApi()
    val json = Json.toJson(result)
    Ok(json)
  }

  /**
    * GET /api/game
    */
  def getAllGames(): Action[AnyContent] = Action { request =>
    def executeApi(): List[GameDescription] = {
      api.getAllGames()
    }

    val result = executeApi()
    val json = Json.toJson(result)
    Ok(json)
  }

  /**
    * GET /api/game/:uuid
    */
  def getGame(uuid: String): Action[AnyContent] = Action { request =>
    def executeApi(): Game = {
      api.getGame(uuid)
    }

    val result = executeApi()
    val json = Json.toJson(result)
    Ok(json)
  }

  /**
    * GET /api/game/:uuid/join
    */
  def joinGame(uuid: String): Action[AnyContent] = Action { request =>
    def executeApi(): List[Game] = {
      api.joinGame(uuid)
    }

    val result = executeApi()
    val json = Json.toJson(result)
    Ok(json)
  }

  /**
    * GET /api/game/:uuid/start
    */
  def startGame(uuid: String): Action[AnyContent] = Action { request =>
    def executeApi(): List[Game] = {
      api.startGame(uuid)
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
