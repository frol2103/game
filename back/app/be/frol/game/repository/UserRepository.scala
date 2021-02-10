package be.frol.game.repository

import be.frol.game.DbContext
import be.frol.game.model.UserWithReferences
import be.frol.game.tables.Tables
import com.google.inject.Inject
import play.api.db.slick.DatabaseConfigProvider

import scala.concurrent.ExecutionContext

class UserRepository @Inject()(
                                val dbConfigProvider: DatabaseConfigProvider,
                              )(implicit executionContext: ExecutionContext)
  extends DbContext {

  import api._


  def add(p: Tables.UserRow)(implicit executionContext: ExecutionContext) = {
    ((Tables.User returning Tables.User.map(_.id)
      into ((v, id) => v.copy(id = id))) += p)
  }

  def userWihtRef(userUuid: String) = {
    Tables.User.filter(_.uuid === userUuid)
      .joinLeft(Tables.UserReference).on(_.id === _.fkUserId)
      .result
      .map(_.groupBy(_._1).map { case (k, v) => UserWithReferences(k, v.map(_._2).flatten) }.head)
  }

  def useByRef(reference: String, service:String) = {
    Tables.User
      .join(Tables.UserReference).on(_.id === _.fkUserId)
      .filter{case (u, r) => r.reference === reference && r.service === service}
      .result.map(_.groupBy(_._1).map { case (k, v) => UserWithReferences(k, v.map(_._2)) }.headOption)
  }

}
