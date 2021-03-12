package com.andreystar.library.service;

import com.andreystar.library.dao.ReaderRepository;
import com.andreystar.library.entity.Reader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Service
public class ReaderServiceImpl implements ReaderService {
	
	@Resource
	private ReaderRepository readerRepository;
	
	@Autowired
	public ReaderServiceImpl(ReaderRepository readerRepository) {
		this.readerRepository = readerRepository;
	}
	
	@Override
	public List<Reader> findAll() {
		return readerRepository.findAll();
	}
	
	@Override
	public Reader findById(int id) {
		Optional<Reader> result = readerRepository.findById(id);
		if (result.isPresent()) {
			return result.get();
		}
		throw new RuntimeException("Did not find reader with id: " + id);
	}
	
	@Override
	public void save(Reader employee) {
		readerRepository.save(employee);
	}
	
	@Override
	public void deleteById(int id) {
		readerRepository.deleteById(id);
	}
}
