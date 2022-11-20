package com.rodyapal.schema

import com.apurebase.kgraphql.schema.dsl.SchemaBuilder
import com.rodyapal.model.Book
import com.rodyapal.model.BookInput
import com.rodyapal.model.BookRepository

fun SchemaBuilder.schemaValue() {
	val repository = BookRepository()

	query("hello") {
		resolver { -> "world" }
	}
	query("book") {
		description = "Get a book from list by id"
		resolver { id: Int ->
			repository.getBy { it.id == id }[0]
		}
	}
	query("books") {
		description = "Get a subset of books from list"
		resolver { ->
			repository.getAll()
		}
	}
	query("booksByTitle") {
		description = "Get all books by respective titles"
		resolver { title: String ->
			repository.getBy { it.title == title }
		}
	}
	type<Book> {
		description = "Book object with the attributes id, title, author name and genre"
	}
}