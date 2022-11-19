package com.rodyapal.plugins

import com.rodyapal.db.AppDatabase
import io.ktor.server.html.*
import kotlinx.html.*
import io.ktor.server.application.*
import io.ktor.server.routing.*
import org.ktorm.entity.toList

fun Application.configureTemplating() {

	routing {
		get("/log") {
			call.respondHtml {
				val messages = AppDatabase.messages.toList()
				body {
					ul {
						for (message in messages) {
							li { +"Message(id: ${message.id}, user: ${message.username}, content: ${message.content})" }
						}
					}
				}
			}
		}
		get("/") {
			call.respondHtml {
				body {
					script(type = ScriptType.textJavaScript) {
						unsafe {
							+"""
								let socket = new WebSocket('ws://localhost:8080/chat');
								socket.onopen = function(e) {
								  console.log('Connected to WebSocket');
								};
								socket.onmessage = function(event) {
								  console.log('Message received:');
								};
								socket.onclose = function(event) {
								  if (event.wasClean) {
									alert('Connection closed cleanly');
								  } else {
									alert('Connection died');
								  }
								};
								socket.onerror = function(error) {
								  alert(`[error]`);
								};
								function onBtnClick() {
									socket.send(document.getElementById('input').value);
									document.getElementById('input').value = '';
								}
							""".trimIndent()
						}
					}
					textArea {
						id = "input"
					}
					button {
						onClick = """onBtnClick()"""
						+"Send"
					}
				}
			}
		}
	}
}
