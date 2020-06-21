package org.fearandloathing.services

import java.util.Optional

import org.fearandloathing.dto.Article
import org.fearandloathing.dto.Converter._
import org.fearandloathing.repositories.ArticleRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Service

import scala.jdk.CollectionConverters._

trait ArticleService {
  def listArticles(): Iterable[Article]
  def getArticle(id: Long): Optional[Article]
  def createArticle(article: Article): Long
  def allArticlesOfUser(userId: Long): Iterable[Article]
  def searchArticles(title: String): Iterable[Article]
}

@Service
class ArticleServiceImpl(@Autowired private val articleRepository: ArticleRepository) extends ArticleService {

  @PreAuthorize("hasRole('user')")
  def listArticles(): Iterable[Article] = {
    articleRepository.findAll.asScala.map(convert(_))
  }

  @PreAuthorize("hasRole('user')")
  def getArticle(id: Long): Optional[Article] = {
    articleRepository.findById(id).map(convert(_))
  }

  @PreAuthorize("hasRole('user')")
  def createArticle(article: Article): Long = {
    articleRepository.save(convert(article))
    article.id
  }

  @PreAuthorize("hasRole('user')")
  def allArticlesOfUser(userId: Long): Iterable[Article] = {
    articleRepository.findArticlesByAuthor(userId).asScala.map(convert(_))
  }

  @PreAuthorize("hasRole('user')")
  def searchArticles(title: String): Iterable[Article] = {
    articleRepository.findArticlesByTitle(title).asScala.map(convert(_))
  }

}
