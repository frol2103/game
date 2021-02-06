package be.frol.chaman

import model.EsquisserGame

@javax.annotation.Generated(value = Array("org.openapitools.codegen.languages.ScalaPlayFrameworkServerCodegen"), date = "2021-02-06T08:27:19.178Z[Etc/UTC]")
trait EsquisserApi {
  /**
    * add drawing round in esquisser game
    * @param text the text
    */
  def addDrawingRound(uuid: String, text: String): List[EsquisserGame]

  /**
    * add text round in esquisse game
    * @param text the text
    */
  def addTextRound(uuid: String, text: String): List[EsquisserGame]

  /**
    * Get an esquisse game
    */
  def getGame(uuid: String): List[EsquisserGame]
}
