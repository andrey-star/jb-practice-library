package com.andreystar.library.controller;

import com.andreystar.library.entity.Reader;
import com.andreystar.library.service.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ReaderRestController {
	
	private final String BASE_URL = "/readers";
	
	@Resource
	private final ReaderService readerService;
	
	@Autowired
	public ReaderRestController(ReaderService readerService) {
		this.readerService = readerService;
	}
	
	@GetMapping(BASE_URL)
	public List<Reader> findAll() {
		return readerService.findAll();
	}
	
	@GetMapping(BASE_URL + "/{readerId}")
	public Reader findById(@PathVariable int readerId) {
		Reader reader = readerService.findById(readerId);
		if (reader == null) {
			throw new RuntimeException("Reader id not found: " + readerId);
		}
		return reader;
	}
	
	@PostMapping(BASE_URL)
	public Reader addReader(@RequestBody Reader reader) {
		reader.setId(0);
		readerService.save(reader);
		return reader;
	}
	
	@PutMapping(BASE_URL)
	public Reader updateReader(@RequestBody Reader reader) {
		readerService.save(reader);
		return reader;
	}
	
	@DeleteMapping(BASE_URL + "/{readerId}")
	public String deleteReader(@PathVariable int readerId) {
		Reader reader = readerService.findById(readerId);
		if (reader == null) {
			throw new RuntimeException("Reader id not found: " + readerId);
		}
		readerService.deleteById(readerId);
		return "Deleted reader with id: " + readerId;
	}
	
}
