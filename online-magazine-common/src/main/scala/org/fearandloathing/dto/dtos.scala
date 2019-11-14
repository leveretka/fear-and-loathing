package org.fearandloathing.dto

import java.util.concurrent.CompletableFuture

import org.fearandloathing.entity.{Articles, Comments, CommentsKt, Users}

import scala.beans.BeanProperty
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.jdk.javaapi.FutureConverters

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

  implicit val convertCommentDto: Convertable[Comment, Comments] =
    new Convertable[Comment, Comments] {
      def convert(c: Comment): Comments = {
        val comments = new Comments()
        comments.setId(c.id)
        comments.setArticle(c.article)
        comments.setBody(c.body)
        comments.setAuthor(c.author)
        comments
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

  implicit val convertCommentEntity: Convertable[Comments, Comment] =
    new Convertable[Comments, Comment] {
      def convert(c: Comments): Comment = Comment(c.getId, c.getArticle, c.getBody, c.getAuthor)
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

case class Comment(@BeanProperty id: Long,
                   @BeanProperty article: Long,
                   @BeanProperty body: String,
                   @BeanProperty author: Long) {

  def ~~~ (author: Long): Comment =
    this.copy(author = author)

  def plus(comment: Comment): Comment =
    Comment(id, article, body + comment.body, author)

}

class ExtendedComment extends Comment(0,0,"", 0)

class CommentContainer[C <: Comment](c: C) {
  def comment: C = c
}

trait A {
  def a(): String

  def b(): Future[String] = Future.apply("Hello!")

  def c(): CompletableFuture[String] = CommentsKt.doSthAsync()

  def d() = FutureConverters.asScala(CommentsKt.doSthAsync())
}

class SuperExtendedComment extends Comment(0,0,"", 0) with A {
  override def a(): String = "aaa"
}

object SuperExtendedComment {
  val x = "XXX"
}