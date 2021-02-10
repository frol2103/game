package be.frol.game.api

import be.frol.game.model.User
import be.frol.game.model.UserAssociation

@javax.annotation.Generated(value = Array("org.openapitools.codegen.languages.ScalaPlayFrameworkServerCodegen"), date = "2021-02-10T22:47:12.422Z[Etc/UTC]")
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
    * social login
    * @param userAssociation the association infos
    */
  def socialLogin(userAssociation: UserAssociation): Unit
}
