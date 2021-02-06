package be.frol.game

import be.frol.game.error.{AuthentificationError, FunctionalError}
import play.api.Logging
import play.api.http.HttpErrorHandler
import play.api.libs.json.Json
import play.api.mvc.Results.{BadRequest, InternalServerError, Unauthorized}
import play.api.mvc.{RequestHeader, Result}

import javax.inject.Singleton
import scala.concurrent.Future

@Singleton
class ErrorHandler extends HttpErrorHandler with Logging{
  override def onClientError(request: RequestHeader, statusCode: Int, message: String): Future[Result] = {
    Future.successful(InternalServerError(message))
  }

  override def onServerError(request: RequestHeader, exception: Throwable): Future[Result] = {
    exception match {
      case a: AuthentificationError => Future.successful(Unauthorized)
      case e: FunctionalError => Future.successful(BadRequest(Json.toJson(Map("error" ->  e.getMessage))))
      case _ => {
        logger.error("Server error", exception)
        Future.successful(InternalServerError(exception.getMessage))
      }
    }
  }
}
