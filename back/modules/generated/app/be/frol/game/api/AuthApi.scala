package be.frol.game.api

import be.frol.game.model.User

@javax.annotation.Generated(value = Array("org.openapitools.codegen.languages.ScalaPlayFrameworkServerCodegen"), date = "2021-02-10T16:42:03.351Z[Etc/UTC]")
trait AuthApi {
  /**
    * connected user
    */
  def getConnectedUser(): User

  /**
    * login
    * @param username login information
    */
  def login(username: String): Unit
}
