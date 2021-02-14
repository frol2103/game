package be.frol.game.model

case class Score(userUuid:String, category:String, amount:Int) {
  def +(score:Score) = copy(amount = amount + score.amount)

}
