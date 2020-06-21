package org.fearandloathing.entity

import java.io.Serializable
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