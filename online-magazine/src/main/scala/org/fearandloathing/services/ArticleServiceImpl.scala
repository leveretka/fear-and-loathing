package org.fearandloathing.services

import org.fearandloathing.entity.Articles
import org.fearandloathing.repositories.ArticleRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Service

import scala.collection.JavaConverters._

trait ArticleService {
  def listArticles(): Iterable[Articles]
  def getArticle(id: Long): Articles
  def createArticle(article: Articles): Long
  def allArticlesOfUser(userId: Long): Iterable[Articles]
  def searchArticles(title: String): Iterable[Articles]
}

@Service
class ArticleServiceImpl(@Autowired private val articleRepository: ArticleRepository) extends ArticleService {

  @PreAuthorize("hasRole('user')")
  def listArticles(): Iterable[Articles] = {
    articleRepository.findAll.asScala
  }

  @PreAuthorize("hasRole('user')")
  def getArticle(id: Long): Articles = {
    articleRepository.findOne(id)
  }

  @PreAuthorize("hasRole('user')")
  def createArticle(article: Articles): Long = {
    articleRepository.save(article)
    article.id
  }

  @PreAuthorize("hasRole('user')")
  def allArticlesOfUser(userId: Long): Iterable[Articles] = {
    articleRepository.findArticlesByAuthor(userId).asScala
  }

  @PreAuthorize("hasRole('user')")
  def searchArticles(title: String): Iterable[Articles] = {
    articleRepository.findArticlesByTitle(title).asScala
  }

}
