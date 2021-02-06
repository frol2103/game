package be.frol.game.api

import be.frol.game.model.LostInTranslationGame

@javax.annotation.Generated(value = Array("org.openapitools.codegen.languages.ScalaPlayFrameworkServerCodegen"), date = "2021-02-06T14:29:07.988Z[Etc/UTC]")
trait LostInTranslationApi {
  /**
    * add drawing round in lostInTranslation game
    * @param text the text
    */
  def addDrawingRound(uuid: String, text: String): List[LostInTranslationGame]

  /**
    * add text round in LostInTranslation game
    * @param text the text
    */
  def addTextRound(uuid: String, text: String): List[LostInTranslationGame]

  /**
    * Get an LostInTranslation game
    */
  def getGame(uuid: String): List[LostInTranslationGame]
}
