package be.frol.game.api

import be.frol.game.model.User
import be.frol.game.model.UserAssociation

@javax.annotation.Generated(value = Array("org.openapitools.codegen.languages.ScalaPlayFrameworkServerCodegen"), date = "2021-02-10T23:20:48.736Z[Etc/UTC]")
trait AuthApi {
  /**
    * connected user
    */
  def getConnectedUser(): User

  /**
    * link to external account
    * @param userAssociation the association infos
    */
  def linkToExternalAccount(userAssociation: UserAssociation): User

  /**
    * login
    * @param username login information
    */
  def login(username: String): Unit

  /**
    * logout
    */
  def logout(): Unit

  /**
    * social login
    * @param userAssociation the association infos
    */
  def socialLogin(userAssociation: UserAssociation): Unit
}
