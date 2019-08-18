package org.fearandloathing.controllers

import java.lang.Iterable

import javax.sql.DataSource
import org.fearandloathing.dto.User
import org.fearandloathing.services.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.{HttpHeaders, HttpStatus, ResponseEntity}
import org.springframework.web.bind.annotation._

import scala.collection.JavaConverters._

@RestController
@RequestMapping(path = Array("/api"))
class UserController(@Autowired private val userService: UserService, @Autowired private val dataSource: DataSource) {

  @GetMapping(path = Array("/users"))
  def getAllUsers: Iterable[User] = {
    userService.listUsers().toList.asJava
  }
  
  @GetMapping(path = Array("/users/{id}"))
  def getUser(@PathVariable id: Long): User = {
    userService.getUser(id)
  }

  @PostMapping(path = Array("/users"))
  def createUser(@RequestBody user: User): ResponseEntity[Long] = {
    val id = userService.createUser(user)
    new ResponseEntity(id, new HttpHeaders, HttpStatus.CREATED)
  }

}