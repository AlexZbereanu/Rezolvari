package com.sd.laborator.microservices

import com.sd.laborator.components.RabbitMqController
import com.sd.laborator.interfaces.LibraryDAO
import com.sd.laborator.interfaces.LibraryPrinter
import org.springframework.amqp.core.AmqpTemplate
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class LibraryPrinterMicroservice {
    @Autowired
    private lateinit var libraryDAO: LibraryDAO

    @Autowired
    private lateinit var libraryPrinter: LibraryPrinter

    @Autowired
    private lateinit var rabbitMqController: RabbitMqController

    @Autowired
    private lateinit var amqpTemplate: AmqpTemplate

    @RequestMapping("/print", method = [RequestMethod.GET])
    @ResponseBody
    fun customPrint(@RequestParam(required = true, name = "format", defaultValue = "") format: String): String {
        libraryDAO.createBookTable()
        return when(format) {
            "html" -> libraryPrinter.printHTML(libraryDAO.getBooks())
            "json" -> libraryPrinter.printJSON(libraryDAO.getBooks())
            "raw" -> libraryPrinter.printRaw(libraryDAO.getBooks())
            else -> "Not implemented"
        }
    }

    @RequestMapping("/find", method = [RequestMethod.GET])
    @ResponseBody
    fun customFind(@RequestParam(required = false, name = "author", defaultValue = "") author: String,
                   @RequestParam(required = false, name = "title", defaultValue = "") title: String,
                   @RequestParam(required = false, name = "publisher", defaultValue = "") publisher: String): String {
        if (author != "")
            return this.libraryPrinter.printJSON(this.libraryDAO.findAllByAuthor(author))
        if (title != "")
            return this.libraryPrinter.printJSON(this.libraryDAO.findAllByTitle(title))
        if (publisher != "")
            return this.libraryPrinter.printJSON(this.libraryDAO.findAllByPublisher(publisher))
        return "Not a valid field"
    }

    @RequestMapping("/find-and-print", method = [RequestMethod.GET])
    @ResponseBody
    fun myFind(@RequestParam(required = true, name = "author", defaultValue = "") author: String,
               @RequestParam(required = true, name = "format", defaultValue = "") format: String): String{
        if (author != "")
            if(format != "")
                return when(format) {
                    "json" -> this.libraryPrinter.printJSON(this.libraryDAO.findAllByAuthor(author))
                    "html" -> this.libraryPrinter.printHTML(this.libraryDAO.findAllByAuthor(author))
                    "raw"  -> this.libraryPrinter.printRaw(this.libraryDAO.findAllByAuthor(author))
                    else   -> "Not implemented"
                }
        return "Not a valid field"
    }

    @RabbitListener(queues = ["\${sqliteexample.rabbitmq.queue}"])
    fun fetchMessage(msg: String){
        val processedMsg = (msg.split(",").map { it.toInt().toChar() }).joinToString(separator="")
        try {
            val (f1, f2, f3) = processedMsg.split(" ")
            val result: String? = when(f1) {
                "print" -> f2
                "find" -> f3
                else -> null
            }
            if (result != null) sendMessage(result)
        } catch (e: Exception) {
            println(e)
        }
    }

    fun sendMessage(message: String){

    }

}