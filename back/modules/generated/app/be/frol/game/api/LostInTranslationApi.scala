package be.frol.game.api

import be.frol.game.model.LostInTranslationGame
import play.api.libs.Files.TemporaryFile

@javax.annotation.Generated(value = Array("org.openapitools.codegen.languages.ScalaPlayFrameworkServerCodegen"), date = "2021-02-08T16:44:35.029Z[Etc/UTC]")
trait LostInTranslationApi {
  /**
    * add drawing round in lostInTranslation game
    */
  def addDrawingRound(uuid: String, file: TemporaryFile): List[LostInTranslationGame]

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
