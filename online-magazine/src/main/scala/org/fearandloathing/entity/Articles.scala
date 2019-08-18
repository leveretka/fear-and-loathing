package org.fearandloathing.entity

import java.io.Serializable
import java.lang.Long

import javax.persistence._

import scala.beans.BeanProperty

@Entity
class Articles extends Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @BeanProperty
  var id: Long = _

  @BeanProperty
  @Column(name = "title")
  var title: String = _

  @BeanProperty
  @Column(name = "body")
  var body: String = _

  @BeanProperty
  @Column(name = "author")
  var author: Long = _

}