package com.sd.laborator.interfaces

import com.sd.laborator.model.Book

interface LibraryPrinter{
    fun printHTML(books: Set<Book>): String
    fun printJSON(books: Set<Book>): String
    fun printRaw(books: Set<Book>): String
    fun printXML(books: Set<Book>): String

}