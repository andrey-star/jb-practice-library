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
	
	@Autowired
	public ReaderRestController(ReaderService readerService) {
		this.readerService = readerService;
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
	
}
