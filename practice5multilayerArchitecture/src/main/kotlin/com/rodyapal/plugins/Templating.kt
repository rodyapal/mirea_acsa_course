package com.rodyapal.plugins

import io.ktor.server.html.*
import kotlinx.html.*
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureTemplating() {

	routing {
		get("/") {
			call.respondHtml {
				body {
					button(name = "test button") {
						script(type = ScriptType.textJavaScript) {
							"""
								console.log('text')
							""".trimIndent()
						}
					}
				}
			}
		}
	}
}
