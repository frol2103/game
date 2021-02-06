package be.frol.game.api

import be.frol.game.model.LostInTranslationGame

/**
  * Provides a default implementation for [[LostInTranslationApi]].
  */
@javax.annotation.Generated(value = Array("org.openapitools.codegen.languages.ScalaPlayFrameworkServerCodegen"), date = "2021-02-06T14:29:07.988Z[Etc/UTC]")
class LostInTranslationApiImpl extends LostInTranslationApi {
  /**
    * @inheritdoc
    */
  override def addDrawingRound(uuid: String, text: String): List[LostInTranslationGame] = {
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
