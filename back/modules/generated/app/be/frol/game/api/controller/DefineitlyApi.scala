package be.frol.game.api.controller

import be.frol.game.api.model.DefineItLyGame
import be.frol.game.api.model.StringWrapper

@javax.annotation.Generated(value = Array("org.openapitools.codegen.languages.ScalaPlayFrameworkServerCodegen"), date = "2021-02-14T14:44:44.961Z[Etc/UTC]")
trait DefineitlyApi {
  /**
    * Add a definitely question
    * @param text the question
    */
  def addQuestion(uuid: String, text: StringWrapper): DefineItLyGame

  /**
    * Add a definitely response to a question
    * @param text the response
    */
  def addResponse(uuid: String, questionUuid: String, text: StringWrapper): DefineItLyGame

  /**
    * Chose a response to a question
    * @param responseUuid the response uuid
    */
  def choseResponse(uuid: String, questionUuid: String, responseUuid: StringWrapper): DefineItLyGame

  /**
    * Get a DefineItLy game
    */
  def getGame(uuid: String, withHistory: Option[Boolean]): DefineItLyGame
}
