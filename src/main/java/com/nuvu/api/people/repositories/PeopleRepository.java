package com.nuvu.api.people.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nuvu.api.people.entities.People;

@Repository
public interface PeopleRepository extends JpaRepository<People, Long> {

	public People findPeopleById(Long id);

}
