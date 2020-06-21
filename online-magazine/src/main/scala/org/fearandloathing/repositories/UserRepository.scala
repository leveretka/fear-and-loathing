package org.fearandloathing.repositories

import org.fearandloathing.entity.Users
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
trait UserRepository extends CrudRepository[Users, java.lang.Long] {

  def findUserByUsername(username: String): Users

}

