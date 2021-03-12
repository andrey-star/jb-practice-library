package com.andreystar.library.service;

import com.andreystar.library.entity.Reader;

import java.util.List;

public interface ReaderService {

	List<Reader> findAll();
	
	Reader findById(int id);
	
	void save(Reader reader);
	
	void deleteById(int id);

}
