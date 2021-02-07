package be.frol.game.api

import be.frol.game.model.Game
import be.frol.game.model.GameDescription

@javax.annotation.Generated(value = Array("org.openapitools.codegen.languages.ScalaPlayFrameworkServerCodegen"), date = "2021-02-07T13:39:39.939Z[Etc/UTC]")
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
  def joinGame(uuid: String): List[Game]

  /**
    * start the game
    */
  def startGame(uuid: String): List[Game]
}
