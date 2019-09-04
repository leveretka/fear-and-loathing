package org.fearandloathing.controllers

import org.fearandloathing.dto.Comment
import org.fearandloathing.services.CommentService
import org.fearandloathing.services.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(path = ["/api"])
class CommentController (@Autowired private val commentService: CommentService,
                         @Autowired private val userService: UserService) {

    @GetMapping(path = ["/comments"])
    fun getAllComments(@RequestParam(required = false) author: String?,
                       @RequestParam(required = false) search: String?
    ): Iterable<Comment> {
        val comments = when (author) {
            is String -> commentService.allCommentsOfUser(author.toLong())
            else -> commentService.listComments()
        }

        return when(search)  {
            is String -> comments.filter { it.body.contains(search) }
            else -> comments
        }
    }

    @GetMapping(path = ["/comments/{title}"])
    fun getArticleComments(@PathVariable title: String): Iterable<Comment> =
        commentService.searchComments(title)


    @PostMapping(path = ["/comments"])
    fun addComment(@RequestBody comment: Comment, authentication: Authentication): ResponseEntity<Long> {
        val userId = userService.getUserByName(authentication.name).id
        val copiedComment = comment.`$tilde$tilde$tilde`(userId)
        val id = commentService.createComment(copiedComment)
        return ResponseEntity(id, HttpHeaders(), HttpStatus.CREATED)
    }
}