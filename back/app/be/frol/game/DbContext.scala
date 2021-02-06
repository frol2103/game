package be.frol.game

import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile

import java.util.UUID

trait DbContext extends HasDatabaseConfigProvider[JdbcProfile]{

  protected val dbConfigProvider: DatabaseConfigProvider
  protected lazy val api = dbConfig.profile.api

  def uuid() = UUID.randomUUID().toString
}
