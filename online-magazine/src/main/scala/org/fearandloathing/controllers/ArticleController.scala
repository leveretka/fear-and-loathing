package org.fearandloathing.controllers

import java.lang.Iterable

import collection.JavaConverters._
import javax.sql.DataSource
import org.fearandloathing.entity.Articles
import org.fearandloathing.services.{ArticleService, UserService}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.{HttpHeaders, HttpStatus, ResponseEntity}
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.{GetMapping, PathVariable, PostMapping, RequestBody, RequestMapping, RequestParam, RestController}

@RestController
@RequestMapping(path = Array("/api"))
class ArticleController (@Autowired private val articleService: ArticleService,
                         @Autowired private val userService: UserService,
                         @Autowired private val dataSource: DataSource) {

  @GetMapping(path = Array("/articles"))
  def getAllArticle(@RequestParam(required = false) author: String,
                    @RequestParam(required = false) search: String
                   ): Iterable[Articles] = {
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
  def getArticle(@PathVariable id: Long): Articles = {
    articleService.getArticle(id)
  }

  @GetMapping(path = Array("/articles/title/{title}"))
  def getArticlesByTitle(@PathVariable title: String): Iterable[Articles] = {
    articleService.searchArticles(title).asJava
  }

  @GetMapping(path = Array("/articles/author/{author}"))
  def getArticlesByTitle(@PathVariable author: Long): Iterable[Articles] = {
    articleService.allArticlesOfUser(author).asJava
  }

  @PostMapping(path = Array("/articles"))
  def createArticle(@RequestBody articles: Articles, authentication: Authentication): ResponseEntity[Long] = {
    val name = authentication.getName
    val userId = userService.getUserByName(name).id
    articles.setAuthor(userId)
    val id = articleService.createArticle(articles)
    new ResponseEntity(id, new HttpHeaders, HttpStatus.CREATED)
  }
}