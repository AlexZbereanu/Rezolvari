package com.sd.laborator.microservices

import com.sd.laborator.components.RabbitMqController
import com.sd.laborator.interfaces.CachingDAO
import org.springframework.amqp.core.AmqpTemplate
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller

@Controller
class CachingMicroservice {
    @Autowired
    private lateinit var cachingDAO: CachingDAO

    @Autowired
    private lateinit var rabbitMqController: RabbitMqController

    @Autowired
    private lateinit var amqpTemplate: AmqpTemplate

    @RabbitListener(queues = ["\${sqliteexample.rabbitmq.queue}"])
    fun fetchMessage(msg: String){

    }

    fun sendMessage(message: String){

    }
}