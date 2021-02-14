package be.frol.game.api.controller

import be.frol.game.api.model.User
import be.frol.game.api.model.UserAssociation

/**
  * Provides a default implementation for [[AuthApi]].
  */
@javax.annotation.Generated(value = Array("org.openapitools.codegen.languages.ScalaPlayFrameworkServerCodegen"), date = "2021-02-14T14:36:31.590Z[Etc/UTC]")
class AuthApiImpl extends AuthApi {
  /**
    * @inheritdoc
    */
  override def getConnectedUser(): User = {
    // TODO: Implement better logic

    User(None, None, None, None)
  }

  /**
    * @inheritdoc
    */
  override def linkToExternalAccount(userAssociation: UserAssociation): User = {
    // TODO: Implement better logic

    User(None, None, None, None)
  }

  /**
    * @inheritdoc
    */
  override def login(username: String): Unit = {
    // TODO: Implement better logic

    
  }

  /**
    * @inheritdoc
    */
  override def logout(): Unit = {
    // TODO: Implement better logic

    
  }

  /**
    * @inheritdoc
    */
  override def socialLogin(userAssociation: UserAssociation): Unit = {
    // TODO: Implement better logic

    
  }
}
