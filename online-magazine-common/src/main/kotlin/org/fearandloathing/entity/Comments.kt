package org.fearandloathing.entity

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.future.future
import java.io.Serializable
import java.util.concurrent.CompletableFuture
import javax.persistence.*

@Entity
class Comments: Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Column(name = "article")
    var article: Long? = null

    @Column(name = "body")
    var body: String? = null

    @Column(name = "author")
    var author: Long? = null
}

suspend fun doSth() {
    delay(500)
    print("I'm done!")
    "Done"
}

fun doSthAsync(): CompletableFuture<String> =
        GlobalScope.future {
            delay(500)
            print("I'm done!")
            "Done"
        }