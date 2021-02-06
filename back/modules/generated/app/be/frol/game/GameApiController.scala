package be.frol.game

import org.openapitools.OpenApiExceptions
import javax.inject.{Inject, Singleton}
import play.api.libs.json._
import play.api.mvc._
import model.Game
import model.GameDescription

@javax.annotation.Generated(value = Array("org.openapitools.codegen.languages.ScalaPlayFrameworkServerCodegen"), date = "2021-02-06T08:46:12.954Z[Etc/UTC]")
@Singleton
class GameApiController @Inject()(cc: ControllerComponents, api: GameApi) extends AbstractController(cc) {
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
