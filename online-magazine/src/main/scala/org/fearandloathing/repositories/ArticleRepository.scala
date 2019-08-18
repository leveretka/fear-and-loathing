package org.fearandloathing.repositories

import java.lang.{Iterable, Long}

import org.fearandloathing.entity.Articles
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
trait ArticleRepository extends CrudRepository[Articles, Long] {

  def findArticlesByAuthor(author: Long): Iterable[Articles]
  def findArticlesByTitle(title: String): Iterable[Articles]

}
