package org.fearandloathing.entity

import java.io.Serializable

import javax.persistence._

import scala.beans.BeanProperty

@Entity
class Users extends Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @BeanProperty
  var id: java.lang.Long = _

  @BeanProperty
  @Column(name = "username")
  var username: String = _

  @BeanProperty
  @Column(name = "password")
  var password: String = _

  @BeanProperty
  @Column(name = "enabled")
  var enabled: Boolean = _

}