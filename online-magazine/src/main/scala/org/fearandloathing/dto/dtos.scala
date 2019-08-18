package org.fearandloathing.dto

import org.fearandloathing.entity.{Articles, Users}

import scala.beans.BeanProperty

trait Convertable[A,B] {
  def convert(a: A): B
}

object Convertable {
  def convert[A,B](a: A)(implicit c: Convertable[A,B]): B = c.convert(a)

  implicit val convertUserDto: Convertable[User, Users] =
    new Convertable[User, Users] {
      def convert(u: User): Users = {
        val users = new Users()
        users.id = u.id
        users.username = u.username
        users.password = u.password
        users.enabled = u.enabled
        users
      }
    }

  implicit val convertArticleDto: Convertable[Article, Articles] =
    new Convertable[Article, Articles] {
      def convert(a: Article): Articles = {
        val articles = new Articles()
        articles.id = a.id
        articles.title = a.title
        articles.body = a.body
        articles.author = a.author
        articles
      }
    }

  implicit val convertUserEntity: Convertable[Users, User] =
    new Convertable[Users, User] {
      def convert(u: Users): User = User(u.id, u.username, u.password, u.enabled)
    }

  implicit val convertArticleEntity: Convertable[Articles, Article] =
    new Convertable[Articles, Article] {
      def convert(a: Articles): Article = Article(a.id, a.title, a.body, a.author)
    }
}

case class User(@BeanProperty id: Long,
                @BeanProperty username: String,
                @BeanProperty password: String,
                @BeanProperty enabled: Boolean)

case class Article(@BeanProperty id: Long,
                   @BeanProperty title: String,
                   @BeanProperty body: String,
                   @BeanProperty author: Long)
