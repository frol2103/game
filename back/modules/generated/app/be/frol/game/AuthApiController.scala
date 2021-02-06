package be.frol.game

import org.openapitools.OpenApiExceptions
import javax.inject.{Inject, Singleton}
import play.api.libs.json._
import play.api.mvc._
import model.AuthUser

@javax.annotation.Generated(value = Array("org.openapitools.codegen.languages.ScalaPlayFrameworkServerCodegen"), date = "2021-02-06T08:42:09.258Z[Etc/UTC]")
@Singleton
class AuthApiController @Inject()(cc: ControllerComponents, api: AuthApi) extends AbstractController(cc) {
  /**
    * POST /api/login
    */
  def login(): Action[AnyContent] = Action { request =>
    def executeApi(): Unit = {
      val authUser = request.body.asJson.map(_.as[AuthUser]).getOrElse {
        throw new OpenApiExceptions.MissingRequiredParameterException("body", "authUser")
      }
      api.login(authUser)
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
