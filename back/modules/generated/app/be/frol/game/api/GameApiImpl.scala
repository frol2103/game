package be.frol.game.api

import be.frol.game.model.Game
import be.frol.game.model.GameDescription

/**
  * Provides a default implementation for [[GameApi]].
  */
@javax.annotation.Generated(value = Array("org.openapitools.codegen.languages.ScalaPlayFrameworkServerCodegen"), date = "2021-02-11T20:52:08.970Z[Etc/UTC]")
class GameApiImpl extends GameApi {
  /**
    * @inheritdoc
    */
  override def createAGame(game: GameDescription): Game = {
    // TODO: Implement better logic

    Game(None, None)
  }

  /**
    * @inheritdoc
    */
  override def getAllGames(): List[GameDescription] = {
    // TODO: Implement better logic

    List.empty[GameDescription]
  }

  /**
    * @inheritdoc
    */
  override def getGame(uuid: String): Game = {
    // TODO: Implement better logic

    Game(None, None)
  }

  /**
    * @inheritdoc
    */
  override def joinGame(uuid: String): Game = {
    // TODO: Implement better logic

    Game(None, None)
  }

  /**
    * @inheritdoc
    */
  override def startGame(uuid: String): Game = {
    // TODO: Implement better logic

    Game(None, None)
  }
}
