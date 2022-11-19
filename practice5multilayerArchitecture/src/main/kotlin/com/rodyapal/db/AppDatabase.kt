package com.rodyapal.db

import org.ktorm.database.Database
import org.ktorm.entity.sequenceOf

private const val DB_USER = "mirea_acsa"
private const val DB_PASS = "acsa"

object AppDatabase {
	private val database = Database.connect(
		"jdbc:postgresql://localhost:5432/mirea_acsa_five",
		user = DB_USER,
		password = DB_PASS
	)

	val messages get() = database.sequenceOf(Messages)
}