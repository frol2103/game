package be.frol.game

import model.AuthUser

@javax.annotation.Generated(value = Array("org.openapitools.codegen.languages.ScalaPlayFrameworkServerCodegen"), date = "2021-02-06T08:42:09.258Z[Etc/UTC]")
trait AuthApi {
  /**
    * login
    * @param authUser login information
    */
  def login(authUser: AuthUser): Unit
}
