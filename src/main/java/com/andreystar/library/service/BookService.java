package com.andreystar.library.service;

import com.andreystar.library.entity.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
	
	List<Book> findAll();
	
	Optional<Book> findById(int id);
	
	Optional<Book> findByIsbn(String isbn);
	
	void save(Book book);
	
	void deleteById(int id);
	
}
