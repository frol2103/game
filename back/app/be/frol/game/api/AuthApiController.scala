package be.frol.game.api

import be.frol.game.error.FunctionalError
import be.frol.game.mapper.UserMapper
import be.frol.game.tables.Tables
import be.frol.game.utils.DateUtils
import be.frol.game.{DbContext, ParentController}
import com.google.inject.{Inject, Singleton}
import play.api.db.slick.DatabaseConfigProvider
import play.api.mvc.{Action, AnyContent, ControllerComponents}

import java.util.UUID
import scala.concurrent.ExecutionContext
import be.frol.game.utils.OptionUtils._
import play.api.libs.json.Json

@Singleton
class AuthApiController @Inject()(
                                   dbProvider: DatabaseConfigProvider,
                                   val cc: ControllerComponents,
                                 )(implicit
                                   executionContext: ExecutionContext
                                 )
  extends ParentController(cc, dbProvider) with DbContext {
  import api._

  def login() = Action.async(parse.json) { request =>
    val username = request.body.validate[String].getOrElse(throw new FunctionalError("Username required"))
    val uuid = UUID.randomUUID.toString;
    db.run(
      Tables.User += Tables.UserRow(0L,username, uuid, DateUtils.ts)
    ).map(_ => Ok.withSession("user" -> uuid));
  }


  def getConnectedUser() = run { implicit request =>
    db.run(currentUser).map(UserMapper.toDto)
  }

}
