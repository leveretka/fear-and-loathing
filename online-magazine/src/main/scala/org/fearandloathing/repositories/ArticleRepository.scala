package org.fearandloathing.repositories

import org.fearandloathing.entity.Articles
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
trait ArticleRepository extends CrudRepository[Articles, java.lang.Long] {

  def findArticlesByAuthor(author: java.lang.Long): java.lang.Iterable[Articles]
  def findArticlesByTitle(title: String): java.lang.Iterable[Articles]

}
