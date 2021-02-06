package be.frol.game.utils

object TraversableUtils {

  implicit def traversableToRichTraversable[T](t: Traversable[T]) = new AnyRef{
    def maxByOption[B](f:T=>B)(implicit cmp: Ordering[B]) = if(t.isEmpty) None else Some(t.maxBy(f))
    def minByOption[B](f:T=>B)(implicit cmp: Ordering[B]) = if(t.isEmpty) None else Some(t.minBy(f))
  }
}
