package org.fearandloathing.services

import org.fearandloathing.dto.Comment
import org.fearandloathing.dto.`Convertable$`
import org.fearandloathing.repositories.CommentRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

interface CommentService {
    fun listComments(): Iterable<Comment>
    fun getComment(id: Long): Comment
    fun createComment(article: Comment): Long
    fun allCommentsOfUser(userId: Long): Iterable<Comment>
    fun searchComments(title: String): Iterable<Comment>
}

@Service
class CommentServiceImpl(@Autowired private val commentRepository: CommentRepository): CommentService {
    override fun listComments(): Iterable<Comment> = TODO()
//            commentRepository.findAll().map {
//                `Convertable$`.`MODULE$`.convertCommentEntity().convert(it)
//            }

    override fun getComment(id: Long): Comment {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun createComment(article: Comment): Long {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun allCommentsOfUser(userId: Long): Iterable<Comment> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun searchComments(title: String): Iterable<Comment> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}