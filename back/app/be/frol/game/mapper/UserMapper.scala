package be.frol.game.mapper

import be.frol.game.api.model.{User, UserAssociation}
import be.frol.game.model.{RichGame, RichUser, UserWithReferences}
import be.frol.game.tables.Tables

object UserMapper {

  def toDto(u: UserWithReferences): User = {
    new User(Option(u.user.uuid), Option(u.user.name),
      None,
      Option(u.references.map(_.service).map(v => UserAssociation(Option(UserAssociation.LinkType.withName(v)), None)).toList))
  }

  def toDto(u: Tables.UserRow): User = {
    new User(Option(u.uuid), Option(u.name), None, None);
  }

  def toDto(u: RichUser): User = {
    toDto(u.u)
  }

  def toDto(id: Long, g: RichGame) : Option[User]= g.user(id).map(toDto)
  def toDto(id: Option[Long], g: RichGame) : Option[User]= id.flatMap(toDto(_, g))

}
