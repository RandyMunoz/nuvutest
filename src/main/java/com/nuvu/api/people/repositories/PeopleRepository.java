package com.nuvu.api.people.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nuvu.api.people.entities.People;

public interface PeopleRepository extends JpaRepository<People, Long> {

	public People findPeopleById(Long id);

}
