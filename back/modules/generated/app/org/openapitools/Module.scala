package org.openapitools

import be.frol.game.api._
import play.api.inject.{Binding, Module => PlayModule}
import play.api.{Configuration, Environment}

@javax.annotation.Generated(value = Array("org.openapitools.codegen.languages.ScalaPlayFrameworkServerCodegen"), date = "2021-02-06T22:43:22.950Z[Etc/UTC]")
class Module extends PlayModule {
  override def bindings(environment: Environment, configuration: Configuration): Seq[Binding[_]] = Seq(
    bind[AuthApi].to[AuthApiImpl],
    bind[GameApi].to[GameApiImpl],
    bind[LostInTranslationApi].to[LostInTranslationApiImpl]
  )
}
