package be.frol.game.api

import be.frol.game.model.DefineItLyGame

/**
  * Provides a default implementation for [[DefineitlyApi]].
  */
@javax.annotation.Generated(value = Array("org.openapitools.codegen.languages.ScalaPlayFrameworkServerCodegen"), date = "2021-02-11T20:52:08.970Z[Etc/UTC]")
class DefineitlyApiImpl extends DefineitlyApi {
  /**
    * @inheritdoc
    */
  override def addQuestion(uuid: String, text: String): DefineItLyGame = {
    // TODO: Implement better logic

    DefineItLyGame(None, None, None)
  }

  /**
    * @inheritdoc
    */
  override def addResponse(uuid: String, questionUuid: String, text: String): DefineItLyGame = {
    // TODO: Implement better logic

    DefineItLyGame(None, None, None)
  }

  /**
    * @inheritdoc
    */
  override def choseResponse(uuid: String, questionUuid: String, responseUuid: String): DefineItLyGame = {
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
