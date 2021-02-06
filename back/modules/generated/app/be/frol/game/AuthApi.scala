package be.frol.game

import model.AuthUser

@javax.annotation.Generated(value = Array("org.openapitools.codegen.languages.ScalaPlayFrameworkServerCodegen"), date = "2021-02-06T08:46:12.954Z[Etc/UTC]")
trait AuthApi {
  /**
    * login
    * @param authUser login information
    */
  def login(authUser: AuthUser): Unit
}
