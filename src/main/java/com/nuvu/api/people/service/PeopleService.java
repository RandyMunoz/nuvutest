package com.nuvu.api.people.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nuvu.api.people.entities.People;
import com.nuvu.api.people.repositories.PeopleRepository;

@Service
public class PeopleService {

	private static Logger _logger = LoggerFactory.getLogger(PeopleService.class);

	@Autowired
	private PeopleRepository peopleRepository;

	public void save(People people) {
		try {
			peopleRepository.save(people);
		} catch (Exception e) {
			e.printStackTrace();
			_logger.error("Error in method save", e);
		}
	}

	public List<People> getListPeople() {
		return peopleRepository.findAll();
	}

	public People getPeopleById(Long id) {
		return peopleRepository.findPeopleById(id);
	}

	public void delete(Long id) {
		try {
			peopleRepository.deleteById(id);
		} catch (Exception e) {
			e.printStackTrace();
			_logger.error("Error in method delete", e);
		}
	}
}
