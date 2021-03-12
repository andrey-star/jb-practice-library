package com.andreystar.library.controller;

import com.andreystar.library.entity.Book;
import com.andreystar.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api")
public class BookRestController {
	
	private final String BASE_URL = "/books";
	
	@Resource
	private final BookService bookService;
	
	@Autowired
	public BookRestController(BookService bookService) {
		this.bookService = bookService;
	}
	
	@GetMapping(BASE_URL)
	public List<Book> getBooks() {
		return bookService.findAll();
	}
	
	@GetMapping(BASE_URL + "/{bookId}")
	public Book getBook(@PathVariable int bookId) {
		Book book = bookService.findById(bookId);
		if (book == null) {
			throw new RuntimeException("Book id not found: " + bookId);
		}
		return book;
	}
	
	@PostMapping(BASE_URL)
	public Book addBook(@RequestBody Book book) {
		book.setId(0);
		bookService.save(book);
		return book;
	}
	
	@PutMapping(BASE_URL)
	public Book updateBook(@RequestBody Book book) {
		bookService.save(book);
		return book;
	}
	
	@DeleteMapping(BASE_URL + "/{bookId}")
	public String deleteBook(@PathVariable int bookId) {
		Book book = bookService.findById(bookId);
		if (book == null) {
			throw new RuntimeException("Book id not found: " + bookId);
		}
		bookService.deleteById(bookId);
		return "Deleted book with id: " + bookId;
	}
	
}
