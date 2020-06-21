package org.fearandloathing.services

import java.util.Optional

import org.fearandloathing.dto.Converter._
import org.fearandloathing.dto.User
import org.fearandloathing.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.prepost.{PostAuthorize, PreAuthorize}
import org.springframework.stereotype.Service

import scala.collection.JavaConverters._

trait UserService {

  def listUsers(): Iterable[User]

  def getUser(id: Long): Optional[User]

  def getUserByName(name: String): User

  def createUser(users: User): Long
}

@Service
class UserServiceImpl(@Autowired private val userRepository: UserRepository) extends UserService {
  @PreAuthorize("hasRole('admin')")
  def listUsers(): Iterable[User] = {
    userRepository.findAll.asScala.map(convert(_))
  }

  @PreAuthorize("hasRole('user')")
  @PostAuthorize("returnObject.username==principal.username || hasRole('admin')")
  def getUser(id: Long): Optional[User] = {
    userRepository.findById(id).map(convert(_))
  }

  @PreAuthorize("hasRole('user')")
  def getUserByName(name: String): User = {
    convert(userRepository.findUserByUsername(name))
  }

  @PreAuthorize("hasRole('admin')")
  def createUser(user: User): Long = {
    val users = userRepository.save(convert(user))
    users.id
  }
}
