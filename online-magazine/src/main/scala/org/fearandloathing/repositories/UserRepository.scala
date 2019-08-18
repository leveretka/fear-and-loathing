package org.fearandloathing.repositories

import org.springframework.stereotype.Repository
import org.fearandloathing.entity.Users
import org.springframework.data.repository.CrudRepository
import java.lang.Long

@Repository
trait UserRepository extends CrudRepository[Users, Long] {

  def findUserByUsername(username: String): Users

}

