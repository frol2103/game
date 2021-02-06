package be.frol.game

import model.Game
import model.GameDescription

@javax.annotation.Generated(value = Array("org.openapitools.codegen.languages.ScalaPlayFrameworkServerCodegen"), date = "2021-02-06T08:42:09.258Z[Etc/UTC]")
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
