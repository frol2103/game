package be.frol.game.api

import be.frol.game.error.FunctionalError
import be.frol.game.mapper.UserMapper
import be.frol.game.model.{User, UserAssociation}
import be.frol.game.repository.UserRepository
import be.frol.game.tables.Tables
import be.frol.game.tables.Tables.{UserReference, UserReferenceRow}
import be.frol.game.utils.DateUtils
import be.frol.game.{DbContext, ParentController}
import com.google.inject.{Inject, Singleton}
import play.api.db.slick.DatabaseConfigProvider
import play.api.libs.ws.WSClient
import play.api.mvc.{ControllerComponents, Request}

import java.util.UUID
import scala.concurrent.ExecutionContext
import scala.concurrent.duration.DurationInt
import be.frol.game.utils.OptionUtils._
import play.api.libs.json.Json
import slick.dbio.DBIOAction

@Singleton
class AuthApiController @Inject()(
                                   wsClient: WSClient,
                                   dbProvider: DatabaseConfigProvider,
                                   val cc: ControllerComponents,
                                 )(implicit
                                   executionContext: ExecutionContext,
                                   userRepository: UserRepository,
                                 )
  extends ParentController(cc, dbProvider) with DbContext {

  import api._

  def login() = Action.async(parse.json) { request =>
    val username = request.body.validate[String].getOrElse(throw new FunctionalError("Username required"))
    db.run(
      userRepository.add(userWithUsername(username))
    ).map(u => Ok.withSession("user" -> u.uuid));
  }

  private def userWithUsername(username: String) = {
    Tables.UserRow(0L, username,randomUuid().toString, DateUtils.ts)
  }

  def socialLogin() = Action.async(parse.json){ implicit request =>
    getFacebookInfos(readBody[UserAssociation](request)).flatMap(fbInfos =>
      db.run(
      userRepository.useByRef(fbInfos.id, UserAssociation.LinkType.Facebook.toString).flatMap{
        case None => for{
          user <- userRepository.add(userWithUsername(fbInfos.name))
          r <- Tables.UserReference += fbUserReference(user.id, fbInfos)
        } yield user.uuid
        case Some(u) => DBIOAction.successful(u.user.uuid)
      }
    )).map(u => Ok.withSession("user" -> u))
  }


  def linkToExternalAccount() = runWithInput[UserAssociation, User] { implicit request =>
    getFacebookInfos(request).flatMap(result =>
      db.run(
        currentUser.flatMap { u =>
          Tables.UserReference += fbUserReference(u.id, result)
        }.flatMap(_ => currentUserWithRef)
          .map(UserMapper.toDto))
      )
  }

  private def fbUserReference(userId:Long, result: FacebookInfos) = {
    Tables.UserReferenceRow(0L, userId,
      result.id,
      DateUtils.ts,
      UserAssociation.LinkType.Facebook.toString)
  }

  case class FacebookInfos(id:String, name:String){
  }

  private def getFacebookInfos(request: Request[UserAssociation]) = {
    wsClient.url("https://graph.facebook.com/v9.0/me")
      .addQueryStringParameters(
        "access_token" -> request.body.accessToken.getOrThrow("access token required"),
        "fields" -> "id,name")
      .withRequestTimeout(10000.millis)
      .get().map{r =>
        r.json.validate[FacebookInfos](Json.format[FacebookInfos]).asOpt.getOrThrow("error while recovering facebook infos")}

  }

  def getConnectedUser() = run { implicit request =>
    db.run(currentUserWithRef).map(UserMapper.toDto)
  }

}
