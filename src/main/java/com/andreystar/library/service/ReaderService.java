package com.andreystar.library.service;

import com.andreystar.library.entity.Reader;

import java.util.List;
import java.util.Optional;

public interface ReaderService {

	List<Reader> findAll();
	
	Optional<Reader> findById(int id);
	
	void save(Reader reader);
	
	void deleteById(int id);

}
