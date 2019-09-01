package org.fearandloathing.repositories

import org.fearandloathing.entity.Comments
import org.springframework.data.repository.CrudRepository

interface CommentRepository : CrudRepository<Comments, Long> {

    fun findCommentsByAuthor(author: Long): Iterable<Comments>
    fun findCommentsByArticle(article: Long): Iterable<Comments>
}