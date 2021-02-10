package be.frol.game.api

import be.frol.game.model.LostInTranslationGame
import play.api.libs.Files.TemporaryFile

/**
  * Provides a default implementation for [[LostInTranslationApi]].
  */
@javax.annotation.Generated(value = Array("org.openapitools.codegen.languages.ScalaPlayFrameworkServerCodegen"), date = "2021-02-10T16:24:51.935Z[Etc/UTC]")
class LostInTranslationApiImpl extends LostInTranslationApi {
  /**
    * @inheritdoc
    */
  override def addDrawingRound(uuid: String, storyId: String, file: TemporaryFile): LostInTranslationGame = {
    // TODO: Implement better logic

    LostInTranslationGame(Game(None, None), List.empty[LostInTranslationStory])
  }

  /**
    * @inheritdoc
    */
  override def addTextRound(uuid: String, storyId: String, text: String): LostInTranslationGame = {
    // TODO: Implement better logic

    LostInTranslationGame(Game(None, None), List.empty[LostInTranslationStory])
  }

  /**
    * @inheritdoc
    */
  override def getGame(uuid: String): LostInTranslationGame = {
    // TODO: Implement better logic

    LostInTranslationGame(Game(None, None), List.empty[LostInTranslationStory])
  }
}
