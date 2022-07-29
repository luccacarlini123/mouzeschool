package com.mouzetech.mouzeschoolapi.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mouzetech.mouzeschoolapi.domain.model.Matricula;

@Repository
public interface MatriculaRepository extends JpaRepository<Matricula, Long> {

}
