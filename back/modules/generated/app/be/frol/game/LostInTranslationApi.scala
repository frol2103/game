package be.frol.game

import model.LostInTranslationGame

@javax.annotation.Generated(value = Array("org.openapitools.codegen.languages.ScalaPlayFrameworkServerCodegen"), date = "2021-02-06T08:46:12.954Z[Etc/UTC]")
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
