package be.frol.game

import be.frol.game.error.AuthentificationError
import be.frol.game.tables.Tables
import play.api.Logging
import play.api.db.slick.DatabaseConfigProvider
import play.api.libs.json._
import play.api.mvc.{AbstractController, ControllerComponents, Request}

import scala.concurrent.{ExecutionContext, Future}

class ParentController(
                        cc: ControllerComponents,
                        val dbConfigProvider: DatabaseConfigProvider,
                      )(implicit executionContext: ExecutionContext)
  extends AbstractController(cc)
    with Logging
    with DbContext {

  import api._

  def currentUser(implicit request: Request[_]) =
    Tables.User.filter(_.uuid === request.session.get("user").getOrElse(throw new AuthentificationError("Not authentified")))
      .result.head


  def run[Output](block: Request[_] => Future[Output])
                 (implicit w: Writes[Output]) = {
    Action.async(request => block.apply(request).map(r => Ok(Json.toJson(r))))
  }

  def runWithInput[Input, Output](block: Request[Input] => Future[Output])
                                 (implicit r: Reads[Input], w: Writes[Output]) = {
    Action.async(parse.json)(request => block.apply(readBody[Input](r, request)).map(r => Ok(Json.toJson(r))))
  }

  private def readBody[T](reader: Reads[T], request: Request[JsValue]) = {
    request.map(_.validate[T](reader) match {
      case JsSuccess(value, path) => value
      case JsError(errors) => {
        logger.debug("Error while reading body " + errors)
        throw new Exception("Error while reading body")
      }
    })
  }
}
