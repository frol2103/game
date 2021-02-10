package be.frol.game.api

import be.frol.game.mapper.GameMapper
import be.frol.game.model.{Game, GameDescription}
import be.frol.game.repository.{GameRepository, UserRepository}
import be.frol.game.utils.OptionUtils._
import be.frol.game.{DbContext, ParentController}
import com.google.inject.{Inject, Singleton}
import play.api.db.slick.DatabaseConfigProvider
import play.api.mvc.ControllerComponents

import scala.concurrent.ExecutionContext

@Singleton
class GameApiController @Inject()(
                                   dbProvider: DatabaseConfigProvider,
                                   val cc: ControllerComponents,
                                   val gameService: GameRepository,
                                 )(implicit
                                   executionContext: ExecutionContext,
                                   userRepository: UserRepository,
                                 )
  extends ParentController(cc, dbProvider) with DbContext {

  import api._


  def createAGame() = runWithInput[GameDescription, Game] { implicit request =>
    db.run(
      currentUser.flatMap(u =>
        gameService.initGame(
          request.body.gameType.map(_.toString).getOrThrow("game type required"),
          u
        )
      ).transactionally
    ).map(GameMapper.toDto)
  }


  def getAllGames() = run(implicit request =>
    db.run(currentUser.flatMap(c =>
      gameService.getAllGamesFor(c).map(_.map(GameMapper.toDto))
    ))
  )

  def getGame(uuid: String) = run(implicit request => db.run(getGameDto(uuid)))

  def joinGame(uuid: String) = run(implicit request =>
    db.run(
      currentUser
        .flatMap(gameService.join(uuid, _))
        .flatMap(_ => getGameDto(uuid))
    )
  )

  def startGame(uuid: String) = run(implicit request =>
    db.run(
      currentUser
        .flatMap(gameService.start(uuid, _))
        .flatMap(_ => getGameDto(uuid))
    )
  )


  private def getGameDto(uuid: String) = {
    gameService.getGame(uuid).map(GameMapper.toDto)
  }

}
