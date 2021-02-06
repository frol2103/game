package be.frol.chaman

import model.Field

/**
  * Provides a default implementation for [[FieldApi]].
  */
@javax.annotation.Generated(value = Array("org.openapitools.codegen.languages.ScalaPlayFrameworkServerCodegen"), date = "2021-02-06T08:05:12.524Z[Etc/UTC]")
class FieldApiImpl extends FieldApi {
  /**
    * @inheritdoc
    */
  override def getField(): Field = {
    // TODO: Implement better logic

    Field(None, None, None, None)
  }
}
