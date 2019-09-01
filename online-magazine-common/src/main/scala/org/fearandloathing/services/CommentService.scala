package org.fearandloathing.services

import org.fearandloathing.dto.Comment
import java.lang.Iterable

trait CommentService {
    def listComments(): Iterable[Comment]
    def getComment(id: Long): Comment
    def createComment(article: Comment): Long
    def allCommentsOfUser(userId: Long): Iterable[Comment]
    def searchComments(title: String): Iterable[Comment]
}
