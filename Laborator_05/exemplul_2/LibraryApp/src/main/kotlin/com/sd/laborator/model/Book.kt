package com.sd.laborator.model

class Book(private var data: Content) {

    var name: String?
        get() {
            return data.name
        }
        set(value) {
            data.name = value
        }

    var author: String?
        get() {
            return data.author
        }
        set(value) {
            data.author = value
        }

    var publisher: String?
        get() {
            return data.publisher
        }
        set(value) {
            data.publisher = value
        }

    var content: String?
        get() {
            return data.text
        }
        set(value) {
            data.text = value
        }

    fun hasAuthor(author: String): Boolean {
        val regex = author.toRegex()
        return regex.containsMatchIn(data.author.toString())
    }

    fun hasTitle(title: String): Boolean {
        val regex = title.toRegex()
        return regex.containsMatchIn(data.name.toString())
    }

    fun publishedBy(publisher: String): Boolean {
        val regex = publisher.toRegex()
        return regex.containsMatchIn(data.publisher.toString())
    }

}