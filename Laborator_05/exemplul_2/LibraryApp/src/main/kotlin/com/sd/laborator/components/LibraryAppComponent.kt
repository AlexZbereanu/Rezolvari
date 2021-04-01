package com.sd.laborator.components

import com.sd.laborator.interfaces.LibraryDAO
import com.sd.laborator.interfaces.LibraryPrinter
import com.sd.laborator.model.Book
import org.springframework.amqp.core.AmqpTemplate
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.lang.Exception

@Component
class LibraryAppComponent {
    @Autowired
    private lateinit var libraryDAO: LibraryDAO

    @Autowired
    private lateinit var libraryPrinter: LibraryPrinter

    @Autowired
    private lateinit var connectionFactory: RabbitMqConnectionFactoryComponent
    private lateinit var amqpTemplate: AmqpTemplate

    @Autowired
    fun initTemplate() {
        this.amqpTemplate = connectionFactory.rabbitTemplate()
    }

    fun sendMessage(msg: String) {
        this.amqpTemplate.convertAndSend(connectionFactory.getExchange(),
                                         connectionFactory.getRoutingKey(),
                                         msg)
    }

    @RabbitListener(queues = ["\${libraryapp.rabbitmq.queue}"])
    fun recieveMessage(msg: String) {
        // the result needs processing
        val processedMsg = (msg.split(",").map { it.toInt().toChar() }).joinToString(separator="")
        try {
            val (function, parameter) = processedMsg.split(":")
            val result: String? = when(function) {
                "print" -> customPrint(parameter)
                "find" -> customFind(parameter)
                else -> null
            }
            if (result != null) sendMessage(result)
        } catch (e: Exception) {
            println(e)
        }
    }

    fun customPrint(format: String): String {
        return when(format) {
            "html" -> libraryPrinter.printHTML(libraryDAO.getBooks())
            "json" -> libraryPrinter.printJSON(libraryDAO.getBooks())
            "raw" -> libraryPrinter.printRaw(libraryDAO.getBooks())
            "xml" -> libraryPrinter.printXML(libraryDAO.getBooks())
            else -> "Not implemented"
        }
    }

    fun customFind(searchParameter: String): String {
        val (field, value) = searchParameter.split("=")
        return when(field) {
            "authorJson" -> this.libraryPrinter.printJSON(this.libraryDAO.findAllByAuthor(value))
            "authorHTML" -> this.libraryPrinter.printHTML(this.libraryDAO.findAllByAuthor(value))
            "authorRaw" -> this.libraryPrinter.printRaw(this.libraryDAO.findAllByAuthor(value))
            "authorXML" -> this.libraryPrinter.printXML(this.libraryDAO.findAllByAuthor(value))
            "titleJson" -> this.libraryPrinter.printJSON(this.libraryDAO.findAllByTitle(value))
            "titleHTML" -> this.libraryPrinter.printHTML(this.libraryDAO.findAllByTitle(value))
            "titleRaw" -> this.libraryPrinter.printRaw(this.libraryDAO.findAllByTitle(value))
            "titleXML" -> this.libraryPrinter.printXML(this.libraryDAO.findAllByTitle(value))
            "publisherJson" -> this.libraryPrinter.printJSON(this.libraryDAO.findAllByPublisher(value))
            "publisherHTML" -> this.libraryPrinter.printHTML(this.libraryDAO.findAllByPublisher(value))
            "publisherRaw" -> this.libraryPrinter.printRaw(this.libraryDAO.findAllByPublisher(value))
            "publisherXML" -> this.libraryPrinter.printXML(this.libraryDAO.findAllByPublisher(value))
            else -> "Not a valid field"
        }
    }

    fun addBook(book: Book): Boolean {
        return try {
            this.libraryDAO.addBook(book)
            true
        } catch (e: Exception) {
            false
        }
    }

}