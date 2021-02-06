package be.frol.game.api

import be.frol.game.mapper.GameMapper
import be.frol.game.model.{Game, GameDescription}
import be.frol.game.repository.GameRepository
import be.frol.game.tables.Tables
import be.frol.game.utils.OptionUtils._
import be.frol.game.{DbContext, ParentController}
import com.google.inject.{Inject, Singleton}
import play.api.db.slick.DatabaseConfigProvider
import play.api.mvc.ControllerComponents

import scala.concurrent.ExecutionContext

@Singleton
class FileApiController @Inject()(
                                   dbProvider: DatabaseConfigProvider,
                                   val cc: ControllerComponents,
                                 )(implicit
                                   executionContext: ExecutionContext
                                 )
  extends ParentController(cc, dbProvider) with DbContext {

  import api._


  def getFile(id: String) = Action.async { request =>
    db.run(Tables.File.filter(_.id===id).result.head)
      .map(fd => Ok(
          org.apache.commons.io.IOUtils.toByteArray(fd.value.getBinaryStream)).as(fd.mime)
          .withHeaders("Content-Disposition" -> ("attachment; filename=\"" + fd.name + "\"")))
  }

}
