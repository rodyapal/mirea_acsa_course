package com.rodyapal.practice4springsockets

import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.stereotype.Controller
import java.text.SimpleDateFormat
import java.util.*

@Controller
class ChatController {

	@MessageMapping("/chat")
	@SendTo("/topic/messages")
	fun send(message: Message): Message = message
}