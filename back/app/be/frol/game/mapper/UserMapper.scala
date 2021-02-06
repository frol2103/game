package be.frol.game.mapper

import be.frol.game.model.User
import be.frol.game.tables.Tables

object UserMapper {

  def toDto(u:Tables.UserRow) ={
    new User(Option(u.id), Option(u.name),None);
  }

}
