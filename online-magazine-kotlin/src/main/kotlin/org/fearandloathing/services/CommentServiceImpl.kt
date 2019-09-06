package org.fearandloathing.services

import org.fearandloathing.dto.Comment
import org.fearandloathing.dto.`Convertable$`.`MODULE$` as convertable
import org.fearandloathing.entity.Comments
import org.fearandloathing.repositories.CommentRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import scala.collection.JavaConverters.asJavaIterableConverter

@Service
class CommentServiceImpl(@Autowired private val commentRepository: CommentRepository,
                         @Autowired private val articleService: ArticleService): CommentService {
    override fun listComments(): Iterable<Comment> =
            commentRepository.findAll().map { toCommentDto(it) }

    override fun getComment(id: Long): Comment =
            toCommentDto(commentRepository.findOne(id))

    override fun createComment(comment: Comment): Long =
            commentRepository.save(convertable
                    .convertCommentDto().convert(comment)).id!!

    override fun allCommentsOfUser(userId: Long): Iterable<Comment> =
        commentRepository.findCommentsByAuthor(userId).map { toCommentDto(it) }

    override fun searchComments(title: String): Iterable<Comment> {
        val article = articleService.searchArticles(title).find {it.title == title}
        //val articles = asJavaIterableConverter(articleService.searchArticles(title)).asJava()

        return commentRepository.findCommentsByArticle(
                article.map { it.id }.getOrElse { 0L }
        ).map { toCommentDto(it) }
    }

    private fun toCommentDto(it: Comments) =
            convertable.convertCommentEntity().convert(it)
}