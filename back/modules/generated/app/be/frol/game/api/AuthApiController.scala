package be.frol.game.api

import org.openapitools.OpenApiExceptions
import javax.inject.{Inject, Singleton}
import play.api.libs.json._
import play.api.mvc._
import be.frol.game.model.User
import be.frol.game.model.UserAssociation

@javax.annotation.Generated(value = Array("org.openapitools.codegen.languages.ScalaPlayFrameworkServerCodegen"), date = "2021-02-10T22:47:12.422Z[Etc/UTC]")
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
    * POST /api/user/link
    */
  def linkToExternalAccount(): Action[AnyContent] = Action { request =>
    def executeApi(): User = {
      val userAssociation = request.body.asJson.map(_.as[UserAssociation]).getOrElse {
        throw new OpenApiExceptions.MissingRequiredParameterException("body", "userAssociation")
      }
      api.linkToExternalAccount(userAssociation)
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

  /**
    * POST /api/socialLogin
    */
  def socialLogin(): Action[AnyContent] = Action { request =>
    def executeApi(): Unit = {
      val userAssociation = request.body.asJson.map(_.as[UserAssociation]).getOrElse {
        throw new OpenApiExceptions.MissingRequiredParameterException("body", "userAssociation")
      }
      api.socialLogin(userAssociation)
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
