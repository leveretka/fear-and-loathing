package org.fearandloathing.controllers

import java.lang
import java.util.Optional

import javax.sql.DataSource
import org.fearandloathing.dto.Article
import org.fearandloathing.services.{ArticleService, UserService}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.{HttpHeaders, HttpStatus, ResponseEntity}
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation._

import scala.jdk.CollectionConverters._

@RestController
@RequestMapping(path = Array("/api"))
class ArticleController (@Autowired private val articleService: ArticleService,
                         @Autowired private val userService: UserService,
                         @Autowired private val dataSource: DataSource) {

  @GetMapping(path = Array("/articles"))
  def getAllArticle(@RequestParam(required = false) author: String,
                    @RequestParam(required = false) search: String
                   ): lang.Iterable[Article] = {
    val articles = author match {
      case null => articleService.listArticles()
      case x: String => articleService.allArticlesOfUser(x.toLong)
    }
    search match  {
      case null => articles
        .asJava
      case x:String => articles
        .filter(a => a.title.contains(x) || a.body.contains(x))
        .asJava
    }
  }

  @GetMapping(path = Array("/articles/{id}"))
  def getArticle(@PathVariable id: Long): Optional[Article] = {
    articleService.getArticle(id)
  }

  @GetMapping(path = Array("/articles/title/{title}"))
  def getArticlesByTitle(@PathVariable title: String): lang.Iterable[Article] = {
    articleService.searchArticles(title).asJava
  }

  @GetMapping(path = Array("/articles/author/{author}"))
  def getArticlesByTitle(@PathVariable author: Long): lang.Iterable[Article] = {
    articleService.allArticlesOfUser(author).asJava
  }

  @PostMapping(path = Array("/articles"))
  def createArticle(@RequestBody article: Article, authentication: Authentication): ResponseEntity[Long] = {
    val userId = userService.getUserByName(authentication.getName).id
    val id = articleService.createArticle(article.copy(author = userId))
    new ResponseEntity(id, new HttpHeaders, HttpStatus.CREATED)
  }
}