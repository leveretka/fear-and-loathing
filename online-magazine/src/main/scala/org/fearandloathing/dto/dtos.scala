package org.fearandloathing.dto

import org.fearandloathing.entity.{Articles, Comments, Users}

import scala.beans.BeanProperty

trait Converter[A,B] {
  def convert(a: A): B
}

object Converter {
  def convert[A,B](a: A)(implicit c: Converter[A,B]): B = c.convert(a)

  implicit val convertUserDto: Converter[User, Users] =
    (u: User) => {
      val users = new Users()
      users.id = u.id
      users.username = u.username
      users.password = u.password
      users.enabled = u.enabled
      users
    }

  implicit val convertArticleDto: Converter[Article, Articles] =
    (a: Article) => {
      val articles = new Articles()
      articles.id = a.id
      articles.title = a.title
      articles.body = a.body
      articles.author = a.author
      articles
    }

  implicit val convertCommentDto: Converter[Comment, Comments] =
    (c: Comment) => {
      val comments = new Comments()
      comments.setId(c.id)
      comments.setArticle(c.article)
      comments.setBody(c.body)
      comments.setAuthor(c.author)
      comments
    }

  implicit val convertUserEntity: Converter[Users, User] =
    (u: Users) => User(u.id, u.username, u.password, u.enabled)

  implicit val convertArticleEntity: Converter[Articles, Article] =
    (a: Articles) => Article(a.id, a.title, a.body, a.author)

  implicit val convertCommentEntity: Converter[Comments, Comment] =
    (c: Comments) => Comment(c.getId, c.getArticle, c.getBody, c.getAuthor)
}

case class User(@BeanProperty id: java.lang.Long,
                @BeanProperty username: String,
                @BeanProperty password: String,
                @BeanProperty enabled: Boolean)

case class Article(@BeanProperty id: java.lang.Long,
                   @BeanProperty title: String,
                   @BeanProperty body: String,
                   @BeanProperty author: java.lang.Long)

case class Comment(@BeanProperty id: java.lang.Long,
                   @BeanProperty article: java.lang.Long,
                   @BeanProperty body: String,
                   @BeanProperty author: java.lang.Long)
