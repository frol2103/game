package be.frol.game.api.controller

import be.frol.game.api.model.User
import be.frol.game.api.model.UserAssociation

@javax.annotation.Generated(value = Array("org.openapitools.codegen.languages.ScalaPlayFrameworkServerCodegen"), date = "2021-02-19T18:53:26.135Z[Etc/UTC]")
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
