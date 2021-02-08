package be.frol.game.api

import be.frol.game.model.LostInTranslationGame
import play.api.libs.Files.TemporaryFile

/**
  * Provides a default implementation for [[LostInTranslationApi]].
  */
@javax.annotation.Generated(value = Array("org.openapitools.codegen.languages.ScalaPlayFrameworkServerCodegen"), date = "2021-02-08T18:07:22.741Z[Etc/UTC]")
class LostInTranslationApiImpl extends LostInTranslationApi {
  /**
    * @inheritdoc
    */
  override def addDrawingRound(uuid: String, file: TemporaryFile): LostInTranslationGame = {
    // TODO: Implement better logic

    LostInTranslationGame(None, None)
  }

  /**
    * @inheritdoc
    */
  override def addTextRound(uuid: String, text: String): LostInTranslationGame = {
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
