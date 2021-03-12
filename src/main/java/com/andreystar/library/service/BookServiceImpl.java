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
	private BookRepository bookRepository;
	
	@Autowired
	public BookServiceImpl(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}
	
	@Override
	public List<Book> findAll() {
		return bookRepository.findAll();
	}
	
	@Override
	public Book findById(int id) {
		Optional<Book> result = bookRepository.findById(id);
		if (result.isPresent()) {
			return result.get();
		}
		throw new RuntimeException("Did not find book with id: " + id);
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
