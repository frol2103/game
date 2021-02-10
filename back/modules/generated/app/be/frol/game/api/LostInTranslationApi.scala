package be.frol.game.api

import be.frol.game.model.LostInTranslationGame
import play.api.libs.Files.TemporaryFile

@javax.annotation.Generated(value = Array("org.openapitools.codegen.languages.ScalaPlayFrameworkServerCodegen"), date = "2021-02-10T22:47:12.422Z[Etc/UTC]")
trait LostInTranslationApi {
  /**
    * add drawing round in lostInTranslation game
    */
  def addDrawingRound(uuid: String, storyId: String, file: TemporaryFile): LostInTranslationGame

  /**
    * add text round in LostInTranslation game
    * @param text the text
    */
  def addTextRound(uuid: String, storyId: String, text: String): LostInTranslationGame

  /**
    * Get an LostInTranslation game
    */
  def getGame(uuid: String): LostInTranslationGame
}
