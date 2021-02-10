package be.frol.game.utils

import be.frol.game.error.FunctionalError

object OptionUtils {

  implicit def toRichOption[T](o: Option[T]) = new AnyRef {
    def getOrThrow(error: String): T = if (o.isDefined) {
      o.get
    } else throw new FunctionalError(error)
  }

  implicit def optionUtils[T](t: T) = new Object {
    def toOpt(): Option[T] = Option(t);
  }

}
