package org.fearandloathing.services

import collection.JavaConverters._

import org.fearandloathing.entity.Users
import org.fearandloathing.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.prepost.{PostAuthorize, PreAuthorize}
import org.springframework.stereotype.Service

trait UserService {

  def listUsers(): Iterable[Users]

  def getUser(id: Long): Users

  def getUserByName(name: String): Users

  def createUser(users: Users): Long
}

@Service
class UserServiceImpl(@Autowired private val userRepository: UserRepository) extends UserService {
  @PreAuthorize("hasRole('admin')")
  def listUsers(): Iterable[Users] = {
    userRepository.findAll.asScala
  }

  @PreAuthorize("hasRole('user')")
  @PostAuthorize("returnObject.username==principal.username || hasRole('admin')")
  def getUser(id: Long): Users = {
    userRepository.findOne(id)
  }

  @PreAuthorize("hasRole('user')")
  def getUserByName(name: String): Users = {
    userRepository.findUserByUsername(name)
  }

  @PreAuthorize("hasRole('admin')")
  def createUser(users: Users): Long = {
    userRepository.save(users)
    users.id
  }
}
