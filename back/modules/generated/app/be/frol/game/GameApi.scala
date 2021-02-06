package be.frol.game

import model.Game
import model.GameDescription

@javax.annotation.Generated(value = Array("org.openapitools.codegen.languages.ScalaPlayFrameworkServerCodegen"), date = "2021-02-06T08:46:12.954Z[Etc/UTC]")
trait GameApi {
  /**
    * Get all my games
    */
  def getAllGames(): List[GameDescription]

  /**
    * join a games
    */
  def joinGame(uuid: String): List[Game]

  /**
    * start the game
    */
  def startGame(uuid: String): List[Game]
}
