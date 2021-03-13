package com.andreystar.library.controller;

import com.andreystar.library.entity.Book;
import com.andreystar.library.entity.User;
import com.andreystar.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BookRestController {
	
	@Resource
	private final BookService bookService;
	
	@Autowired
	public BookRestController(BookService bookService) {
		this.bookService = bookService;
	}
	
	@GetMapping
	public List<Book> getBooks(@RequestParam(required = false) String isbn) {
		if (isbn != null) {
			Optional<Book> book = bookService.findByIsbn(isbn);
			if (book.isEmpty()) {
				return List.of();
			}
			return List.of(book.get());
		}
		return bookService.findAll();
	}
	
	@GetMapping("/{bookId}")
	public Book getBook(@PathVariable int bookId) {
		Optional<Book> book = bookService.findById(bookId);
		if (book.isEmpty()) {
			throw new RuntimeException("Book id not found: " + bookId);
		}
		return book.get();
	}
	
	@PostMapping
	public Book addBook(@RequestBody Book book) {
		book.setId(0);
		bookService.save(book);
		return book;
	}
	
	@PutMapping
	public Book updateBook(@RequestBody Book book) {
		bookService.save(book);
		return book;
	}
	
	@DeleteMapping("/{bookId}")
	public String deleteBook(@PathVariable int bookId) {
		Optional<Book> bookOptional = bookService.findById(bookId);
		if (bookOptional.isEmpty()) {
			throw new RuntimeException("Book id not found: " + bookId);
		}
		Book book = bookOptional.get();
		for (User user : book.getUsers()) {
			user.getBooks().remove(book);
		}
		bookService.deleteById(bookId);
		return "Deleted book with id: " + bookId;
	}
	
}
