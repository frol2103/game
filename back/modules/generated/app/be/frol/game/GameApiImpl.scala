package be.frol.game

import model.Game
import model.GameDescription

/**
  * Provides a default implementation for [[GameApi]].
  */
@javax.annotation.Generated(value = Array("org.openapitools.codegen.languages.ScalaPlayFrameworkServerCodegen"), date = "2021-02-06T08:42:09.258Z[Etc/UTC]")
class GameApiImpl extends GameApi {
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
  override def joinGame(uuid: String): List[Game] = {
    // TODO: Implement better logic

    List.empty[Game]
  }

  /**
    * @inheritdoc
    */
  override def startGame(uuid: String): List[Game] = {
    // TODO: Implement better logic

    List.empty[Game]
  }
}
