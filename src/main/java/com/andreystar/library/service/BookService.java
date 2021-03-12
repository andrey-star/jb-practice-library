package com.andreystar.library.service;

import com.andreystar.library.entity.Book;

import java.util.List;

public interface BookService {

	List<Book> findAll();
	
	Book findById(int id);
	
	void save(Book book);
	
	void deleteById(int id);

}
