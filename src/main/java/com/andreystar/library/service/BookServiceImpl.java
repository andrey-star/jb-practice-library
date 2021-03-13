package com.andreystar.library.service;

import com.andreystar.library.dao.BookRepository;
import com.andreystar.library.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
	
	@Resource
	private final BookRepository bookRepository;
	
	@Autowired
	public BookServiceImpl(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}
	
	@Override
	public List<Book> findAll() {
		return bookRepository.findAllByOrderByTitle();
	}
	
	@Override
	public Optional<Book> findById(int id) {
		return bookRepository.findById(id);
	}
	
	@Override
	public Optional<Book> findByIsbn(String isbn) {
		return bookRepository.findByIsbn(isbn);
	}
	
	@Override
	public void save(Book book) {
		bookRepository.save(book);
	}
	
	@Override
	public void deleteById(int id) {
		bookRepository.deleteById(id);
	}
}
