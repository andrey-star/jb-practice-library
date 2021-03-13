package com.andreystar.library.controller;

import com.andreystar.library.entity.Book;
import com.andreystar.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

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
	public List<Book> getBooks() {
		return bookService.findAll();
	}
	
	@GetMapping("/{bookId}")
	public Book getBook(@PathVariable int bookId) {
		Book book = bookService.findById(bookId);
		if (book == null) {
			throw new RuntimeException("Book id not found: " + bookId);
		}
		return book;
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
		Book book = bookService.findById(bookId);
		if (book == null) {
			throw new RuntimeException("Book id not found: " + bookId);
		}
		bookService.deleteById(bookId);
		return "Deleted book with id: " + bookId;
	}
	
}
