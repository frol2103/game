package be.frol.game.utils

object MapUtils {

  def groupCommonIndex[K,A,B](m1:Map[K,A], m2:Map[K,B]) = {
    m1.keySet.intersect(m2.keySet).map(k => k -> (m1(k),m2(k))).toMap
  }
}
