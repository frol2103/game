package be.frol.chaman

import model.EsquisserGame

/**
  * Provides a default implementation for [[EsquisserApi]].
  */
@javax.annotation.Generated(value = Array("org.openapitools.codegen.languages.ScalaPlayFrameworkServerCodegen"), date = "2021-02-06T08:27:19.178Z[Etc/UTC]")
class EsquisserApiImpl extends EsquisserApi {
  /**
    * @inheritdoc
    */
  override def addDrawingRound(uuid: String, text: String): List[EsquisserGame] = {
    // TODO: Implement better logic

    List.empty[EsquisserGame]
  }

  /**
    * @inheritdoc
    */
  override def addTextRound(uuid: String, text: String): List[EsquisserGame] = {
    // TODO: Implement better logic

    List.empty[EsquisserGame]
  }

  /**
    * @inheritdoc
    */
  override def getGame(uuid: String): List[EsquisserGame] = {
    // TODO: Implement better logic

    List.empty[EsquisserGame]
  }
}
