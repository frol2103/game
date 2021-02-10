package be.frol.game.mapper

import be.frol.game.model.{RichUser, User}
import be.frol.game.tables.Tables

object UserMapper {

  def toDto(u:Tables.UserRow): User ={
    new User(Option(u.uuid), Option(u.name),None);
  }

  def toDto(u:RichUser): User ={
    toDto(u.u)
  }

}
