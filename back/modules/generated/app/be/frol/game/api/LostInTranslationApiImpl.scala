package be.frol.game.api

import be.frol.game.model.LostInTranslationGame
import play.api.libs.Files.TemporaryFile

/**
  * Provides a default implementation for [[LostInTranslationApi]].
  */
@javax.annotation.Generated(value = Array("org.openapitools.codegen.languages.ScalaPlayFrameworkServerCodegen"), date = "2021-02-07T13:39:39.939Z[Etc/UTC]")
class LostInTranslationApiImpl extends LostInTranslationApi {
  /**
    * @inheritdoc
    */
  override def addDrawingRound(uuid: String, file: TemporaryFile): List[LostInTranslationGame] = {
    // TODO: Implement better logic

    List.empty[LostInTranslationGame]
  }

  /**
    * @inheritdoc
    */
  override def addTextRound(uuid: String, text: String): List[LostInTranslationGame] = {
    // TODO: Implement better logic

    List.empty[LostInTranslationGame]
  }

  /**
    * @inheritdoc
    */
  override def getGame(uuid: String): List[LostInTranslationGame] = {
    // TODO: Implement better logic

    List.empty[LostInTranslationGame]
  }
}
