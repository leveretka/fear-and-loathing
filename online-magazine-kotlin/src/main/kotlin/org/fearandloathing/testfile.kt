package org.fearandloathing

import org.fearandloathing.dto.Comment
import org.fearandloathing.dto.CommentContainer
import org.fearandloathing.dto.SuperExtendedComment
import org.fearandloathing.dto.`SuperExtendedComment$`
import scala.concurrent.Future
import scala.jdk.javaapi.FutureConverters
import java.util.concurrent.CompletableFuture

import scala.compat.java8.`FutureConverters$`.`MODULE$` as futureConverter

//class KotlinComment: C    omment(1000, 1000, "", 1000) // bug

//val failedContainer = CommentContainer(123) //bug

//val cContainer = CommentContainer(KotlinComment())

val superExtendedComment = SuperExtendedComment()

fun SuperExtendedComment.print() = println(this)

fun main() {
    println(superExtendedComment.a())

   `SuperExtendedComment$`.`MODULE$`.x()
    
    CommentContainer(123)

    superExtendedComment.print()


    val future: Future<String> = superExtendedComment.b()
    val future2: CompletableFuture<String> = superExtendedComment.c()

    val javaFuture = FutureConverters.asJava(future)
    val javaFuture1 = futureConverter.toJava(future)

    javaFuture.thenAccept{ print(it)}



    val c1 = Comment(1,1, "A", 1)
    val c2 = Comment(2,1, "B", 1)

    c1.plus(c2)

    c1 + c2

}
