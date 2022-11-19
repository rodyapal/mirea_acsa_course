package com.rodyapal.db

import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.varchar

object Messages : Table<Message>("messages") {
	val id = int("id").primaryKey().bindTo { it.id }
	val username = varchar("username").bindTo { it.username }
	val content = varchar("content").bindTo { it.content }
}