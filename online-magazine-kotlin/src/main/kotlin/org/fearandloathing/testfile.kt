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

    val c1 = Comment(1,1, "A", 1)
    val c2 = Comment(2,1, "B", 1)

    c1.plus(c2)

    c1 + c2


}
