package org.fearandloathing.services

import org.fearandloathing.dto.User

trait UserService {

  def listUsers(): Iterable[User]

  def getUser(id: Long): User

  def getUserByName(name: String): User

  def createUser(users: User): Long
}
