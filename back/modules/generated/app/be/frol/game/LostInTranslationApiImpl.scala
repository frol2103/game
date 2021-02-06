package be.frol.game

import model.LostInTranslationGame

/**
  * Provides a default implementation for [[LostInTranslationApi]].
  */
@javax.annotation.Generated(value = Array("org.openapitools.codegen.languages.ScalaPlayFrameworkServerCodegen"), date = "2021-02-06T08:46:12.954Z[Etc/UTC]")
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
