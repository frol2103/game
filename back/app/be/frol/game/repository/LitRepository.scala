package be.frol.game.repository

import be.frol.game.DbContext
import be.frol.game.tables.Tables
import com.google.inject.Inject
import play.api.db.slick.DatabaseConfigProvider

import scala.concurrent.ExecutionContext

class LitRepository @Inject()(
                               val dbConfigProvider: DatabaseConfigProvider,
                             )(implicit executionContext: ExecutionContext)
  extends DbContext {

  import api._

  def add(p: Tables.LitRoundRow)(implicit executionContext: ExecutionContext) = {
    ((Tables.LitRound returning Tables.LitRound.map(_.id)
      into ((v, id) => v.copy(id = id))) += p)
  }

}
