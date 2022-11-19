package com.rodyapal.db

import org.ktorm.entity.Entity

interface Message : Entity<Message> {
	companion object : Entity.Factory<Message>()
	val id: Int
	var username: String
	var content: String
}