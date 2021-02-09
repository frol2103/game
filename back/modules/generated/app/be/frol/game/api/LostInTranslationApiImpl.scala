package be.frol.game.api

import be.frol.game.model.LostInTranslationGame
import play.api.libs.Files.TemporaryFile

/**
  * Provides a default implementation for [[LostInTranslationApi]].
  */
@javax.annotation.Generated(value = Array("org.openapitools.codegen.languages.ScalaPlayFrameworkServerCodegen"), date = "2021-02-09T21:45:15.552Z[Etc/UTC]")
class LostInTranslationApiImpl extends LostInTranslationApi {
  /**
    * @inheritdoc
    */
  override def addDrawingRound(uuid: String, storyId: String, file: TemporaryFile): LostInTranslationGame = {
    // TODO: Implement better logic

    LostInTranslationGame(None, None)
  }

  /**
    * @inheritdoc
    */
  override def addTextRound(uuid: String, storyId: String, text: String): LostInTranslationGame = {
    // TODO: Implement better logic

    LostInTranslationGame(None, None)
  }

  /**
    * @inheritdoc
    */
  override def getGame(uuid: String): LostInTranslationGame = {
    // TODO: Implement better logic

    LostInTranslationGame(None, None)
  }
}
