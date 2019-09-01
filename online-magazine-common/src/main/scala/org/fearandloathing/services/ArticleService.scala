package org.fearandloathing.services

import org.fearandloathing.dto.Article

trait ArticleService {
  def listArticles(): Iterable[Article]
  def getArticle(id: Long): Article
  def createArticle(article: Article): Long
  def allArticlesOfUser(userId: Long): Iterable[Article]
  def searchArticles(title: String): Iterable[Article]
}