package com.rodyapal.plugins

import com.rodyapal.db.AppDatabase
import com.rodyapal.db.Message
import com.rodyapal.model.Connection
import io.ktor.server.routing.*
import io.ktor.server.websocket.*
import io.ktor.websocket.*
import java.time.Duration
import io.ktor.server.application.*
import org.ktorm.entity.add
import java.util.*
import kotlin.collections.LinkedHashSet

fun Application.configureSockets() {
	install(WebSockets) {
		pingPeriod = Duration.ofSeconds(15)
		timeout = Duration.ofSeconds(15)
		maxFrameSize = Long.MAX_VALUE
		masking = false
	}

	routing {
		val connections = Collections.synchronizedSet<Connection>(LinkedHashSet())
		webSocket("/chat") { // websocketSession
			println("Adding user!")
			val currentConnection = Connection(this)
			connections += currentConnection
			try {
				send("You are connected! There are ${connections.count()} users here.")
				for (frame in incoming) {
					frame as? Frame.Text ?: continue
					val receivedText = frame.readText()
					val textWithUsername = "[${currentConnection.name}]: $receivedText"
					connections.forEach {
						it.session.send(textWithUsername)
					}

					val message = Message {
						username = currentConnection.name
						content = receivedText
					}
					AppDatabase.messages.add(message)
				}
			} catch (e: Exception) {
				println(e.localizedMessage)
			} finally {
				println("Removing $currentConnection!")
				connections -= currentConnection
			}
		}
	}
}
