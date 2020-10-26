package com.nuvu.api.people.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nuvu.api.people.entities.Rol;

public interface RolRepository extends JpaRepository<Rol, Long> {

}
