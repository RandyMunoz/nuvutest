package com.nuvu.api.people.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nuvu.api.people.entities.Rol;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long> {

}
