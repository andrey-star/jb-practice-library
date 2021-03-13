package com.andreystar.library.controller;

import com.andreystar.library.entity.Book;
import com.andreystar.library.entity.Reader;
import com.andreystar.library.service.BookService;
import com.andreystar.library.service.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/readers")
public class ReaderRestController {
	
	@Resource
	private final ReaderService readerService;
	@Resource
	private final BookService bookService;
	
	@Autowired
	public ReaderRestController(ReaderService readerService, BookService bookService) {
		this.readerService = readerService;
		this.bookService = bookService;
	}
	
	@GetMapping
	public List<Reader> getReaders() {
		return readerService.findAll();
	}
	
	@GetMapping("/{readerId}")
	public Reader getReader(@PathVariable int readerId) {
		Reader reader = readerService.findById(readerId);
		if (reader == null) {
			throw new RuntimeException("Reader id not found: " + readerId);
		}
		return reader;
	}
	
	@PostMapping
	public Reader addReader(@RequestBody Reader reader) {
		reader.setId(0);
		readerService.save(reader);
		return reader;
	}
	
	@PutMapping
	public Reader updateReader(@RequestBody Reader reader) {
		readerService.save(reader);
		return reader;
	}
	
	@DeleteMapping("/{readerId}")
	public String deleteReader(@PathVariable int readerId) {
		Reader reader = readerService.findById(readerId);
		if (reader == null) {
			throw new RuntimeException("Reader id not found: " + readerId);
		}
		readerService.deleteById(readerId);
		return "Deleted reader with id: " + readerId;
	}
	
	@GetMapping("/{readerId}/books")
	public Set<Book> getBooks(@PathVariable int readerId) {
		Reader reader = readerService.findById(readerId);
		if (reader == null) {
			throw new RuntimeException("Reader id not found: " + readerId);
		}
		return reader.getBooks();
	}
	
	@PutMapping("/{readerId}/books/{bookId}")
	public Reader addBook(@PathVariable int readerId, @PathVariable int bookId) {
		Reader reader = readerService.findById(readerId);
		if (reader == null) {
			throw new RuntimeException("Reader id not found: " + readerId);
		}
		Book book = bookService.findById(bookId);
		if (book == null) {
			throw new RuntimeException("Book id not found: " + bookId);
		}
		reader.addBook(book);
		readerService.save(reader);
		return reader;
	}
	
}
