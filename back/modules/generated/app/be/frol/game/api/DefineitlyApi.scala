package be.frol.game.api

import be.frol.game.model.DefineItLyGame

@javax.annotation.Generated(value = Array("org.openapitools.codegen.languages.ScalaPlayFrameworkServerCodegen"), date = "2021-02-11T20:52:08.970Z[Etc/UTC]")
trait DefineitlyApi {
  /**
    * Add a definitely question
    * @param text the question
    */
  def addQuestion(uuid: String, text: String): DefineItLyGame

  /**
    * Add a definitely response to a question
    * @param text the response
    */
  def addResponse(uuid: String, questionUuid: String, text: String): DefineItLyGame

  /**
    * Chose a response to a question
    * @param responseUuid the response uuid
    */
  def choseResponse(uuid: String, questionUuid: String, responseUuid: String): DefineItLyGame

  /**
    * Get a DefineItLy game
    */
  def getGame(uuid: String): DefineItLyGame
}
