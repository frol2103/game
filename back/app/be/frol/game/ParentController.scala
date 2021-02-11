package be.frol.game

import be.frol.game.error.AuthentificationError
import be.frol.game.repository.UserRepository
import be.frol.game.tables.Tables
import play.api.Logging
import play.api.db.slick.DatabaseConfigProvider
import play.api.libs.json._
import play.api.mvc.{AbstractController, ControllerComponents, Request}

import scala.concurrent.{ExecutionContext, Future}

class ParentController(
                        cc: ControllerComponents,
                        val dbConfigProvider: DatabaseConfigProvider,
                      )(
                        implicit executionContext: ExecutionContext,
                        userRepository: UserRepository,
                      )
  extends AbstractController(cc)
    with Logging
    with DbContext {

  import api._

  def currentUserUUID(implicit request: Request[_]) = request.session.get("user").getOrElse(throw new AuthentificationError("Not authentified"))

  def currentUser(implicit request: Request[_]) = {
    Tables.User.filter(_.uuid === currentUserUUID)
      .result.head
  }

  def currentUserWithRef(implicit request: Request[_]) = {
    userRepository.userWihtRef(currentUserUUID)
  }


  def run[Output](block: Request[_] => Future[Output])
                 (implicit w: Writes[Output]) = {
    Action.async(request => block.apply(request).map(r => Ok(Json.toJson(r))))
  }

  def runWithInput[Input, Output](block: Request[Input] => Future[Output])
                                 (implicit r: Reads[Input], w: Writes[Output]) = {
    Action.async(parse.json)(request => block.apply(readBody[Input](request)).map(r => Ok(Json.toJson(r))))
  }

  def readBody[T](request: Request[JsValue])(implicit reader: Reads[T]) = {
    request.map(_.validate[T](reader) match {
      case JsSuccess(value, path) => value
      case JsError(errors) => {
        logger.debug("Error while reading body " + errors)
        throw new Exception("Error while reading body")
      }
    })
  }
}
