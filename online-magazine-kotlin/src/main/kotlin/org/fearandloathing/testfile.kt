package org.fearandloathing

import org.fearandloathing.dto.Comment
import org.fearandloathing.dto.CommentContainer
import org.fearandloathing.dto.SuperExtendedComment
import org.fearandloathing.dto.`SuperExtendedComment$`

//class KotlinComment: Comment(1000, 1000, "", 1000) // bug

//val failedContainer = CommentContainer(123) //bug

//val cContainer = CommentContainer(KotlinComment())

val superExtendedComment = SuperExtendedComment()

fun SuperExtendedComment.print() = println(this)

fun main() {
    println(superExtendedComment.a())

   `SuperExtendedComment$`.`MODULE$`.x()

    superExtendedComment.print()
}
