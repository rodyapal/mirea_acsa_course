package com.rodyapal

import com.apurebase.kgraphql.GraphQL
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.rodyapal.plugins.*
import com.rodyapal.schema.schemaValue
import io.ktor.server.application.*

fun main() {
	embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
		.start(wait = true)
}

fun Application.module() {
	install(GraphQL) {
		configureRouting()
		playground = true
		schema {
			schemaValue()
		}
	}
}
