package be.frol.game.api

import be.frol.game.model.User

/**
  * Provides a default implementation for [[AuthApi]].
  */
@javax.annotation.Generated(value = Array("org.openapitools.codegen.languages.ScalaPlayFrameworkServerCodegen"), date = "2021-02-10T16:24:51.935Z[Etc/UTC]")
class AuthApiImpl extends AuthApi {
  /**
    * @inheritdoc
    */
  override def getConnectedUser(): User = {
    // TODO: Implement better logic

    User(None, None, None)
  }

  /**
    * @inheritdoc
    */
  override def login(username: String): Unit = {
    // TODO: Implement better logic

    
  }
}
