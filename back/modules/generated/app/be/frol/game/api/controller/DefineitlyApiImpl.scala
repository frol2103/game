package be.frol.game.api.controller

import be.frol.game.api.model.DefineItLyGame
import be.frol.game.api.model.StringWrapper

/**
  * Provides a default implementation for [[DefineitlyApi]].
  */
@javax.annotation.Generated(value = Array("org.openapitools.codegen.languages.ScalaPlayFrameworkServerCodegen"), date = "2021-02-13T21:54:18.160Z[Etc/UTC]")
class DefineitlyApiImpl extends DefineitlyApi {
  /**
    * @inheritdoc
    */
  override def addQuestion(uuid: String, text: StringWrapper): DefineItLyGame = {
    // TODO: Implement better logic

    DefineItLyGame(None, None, None)
  }

  /**
    * @inheritdoc
    */
  override def addResponse(uuid: String, questionUuid: String, text: StringWrapper): DefineItLyGame = {
    // TODO: Implement better logic

    DefineItLyGame(None, None, None)
  }

  /**
    * @inheritdoc
    */
  override def choseResponse(uuid: String, questionUuid: String, responseUuid: StringWrapper): DefineItLyGame = {
    // TODO: Implement better logic

    DefineItLyGame(None, None, None)
  }

  /**
    * @inheritdoc
    */
  override def getGame(uuid: String): DefineItLyGame = {
    // TODO: Implement better logic

    DefineItLyGame(None, None, None)
  }
}
