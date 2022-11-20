package com.rodyapal.model

import kotlin.random.Random

private val mockBooks = mutableListOf(
	Book("army of the void", "sci-fi", "Riccardo Corbett", 0),
	Book("doctor on mars", "sci-fi", "Leandro Mitchell", 1),
	Book("admiring the east", "nonfiction", "Julie Stott", 2),
	Book("children of space", "sci-fi", "Leandro Mitchell", 3),
	Book("invaders and strangers", "sci-fi", "Quinn Becker", 4),
	Book("ancient chemistry", "nonfiction", "Shelby Bird", 5),
	Book("rebirth of the past", "sci-fi", "Leandro Mitchell", 6),
	Book("end of the ocean", "drama", "Nicola Woodard", 7),
	Book("hunting the north", "adventure", "Kiara Humphreys", 8),
	Book("result of darkness", "horror", "Kayne Dillard", 9),
)

class BookRepository {
	fun addBook(book: Book) = mockBooks.add(book)
	fun deleteBook(book: Book) = mockBooks.remove(book)
	fun getAll(): List<Book> = mockBooks
	fun getBy(predicate: (Book) -> Boolean): List<Book> = mockBooks.filter(predicate)
	fun updateBook(
		targetId: Int, newTitle: String? = null, newGenre: String? = null, newAuthorName: String?
	) = mockBooks
		.find { it.id == targetId }
		?.also { deleteBook(it) }
		?.let {
			addBook(it.from(targetId, newTitle, newGenre, newAuthorName))
		}
}

private fun Book.from(
	newId: Int? = null,
	newTitle: String? = null,
	newGenre: String? = null,
	newAuthorName: String? = null
): Book = Book(
	id = newId ?: (mockBooks.maxOf { it.id } + 1),
	title = newTitle ?: title,
	genre = newGenre ?: genre,
	authorName = newAuthorName ?: authorName
)