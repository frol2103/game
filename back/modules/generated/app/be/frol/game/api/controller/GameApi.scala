package be.frol.game.api.controller

import be.frol.game.api.model.Game
import be.frol.game.api.model.GameDescription

@javax.annotation.Generated(value = Array("org.openapitools.codegen.languages.ScalaPlayFrameworkServerCodegen"), date = "2021-02-14T14:36:31.590Z[Etc/UTC]")
trait GameApi {
  /**
    * create a new game
    * @param game the game to create
    */
  def createAGame(game: GameDescription): Game

  /**
    * Get all my games
    */
  def getAllGames(): List[GameDescription]

  /**
    * get a games
    */
  def getGame(uuid: String): Game

  /**
    * join a games
    */
  def joinGame(uuid: String): Game

  /**
    * start the game
    */
  def startGame(uuid: String): Game
}
