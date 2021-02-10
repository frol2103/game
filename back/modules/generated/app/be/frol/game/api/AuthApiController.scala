package be.frol.game.api

import org.openapitools.OpenApiExceptions
import javax.inject.{Inject, Singleton}
import play.api.libs.json._
import play.api.mvc._
import be.frol.game.model.User

@javax.annotation.Generated(value = Array("org.openapitools.codegen.languages.ScalaPlayFrameworkServerCodegen"), date = "2021-02-10T16:42:03.351Z[Etc/UTC]")
@Singleton
class AuthApiController @Inject()(cc: ControllerComponents, api: AuthApi) extends AbstractController(cc) {
  /**
    * GET /api/user
    */
  def getConnectedUser(): Action[AnyContent] = Action { request =>
    def executeApi(): User = {
      api.getConnectedUser()
    }

    val result = executeApi()
    val json = Json.toJson(result)
    Ok(json)
  }

  /**
    * POST /api/login
    */
  def login(): Action[AnyContent] = Action { request =>
    def executeApi(): Unit = {
      val username = request.body.asJson.map(_.as[String]).getOrElse {
        throw new OpenApiExceptions.MissingRequiredParameterException("body", "username")
      }
      api.login(username)
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
