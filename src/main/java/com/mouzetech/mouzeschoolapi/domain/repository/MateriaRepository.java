package com.mouzetech.mouzeschoolapi.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mouzetech.mouzeschoolapi.domain.model.Materia;
import com.mouzetech.mouzeschoolapi.domain.model.enumeration.GrauEnsino;
import com.mouzetech.mouzeschoolapi.domain.model.enumeration.SerieEnsino;

@Repository
public interface MateriaRepository extends JpaRepository<Materia, Long> {

	Optional<Materia> findByNome(String nome);
	Optional<Materia> findByNomeAndGrauEnsinoAndSerieEnsino(String nome, GrauEnsino grauEnsino, SerieEnsino serieEnsino);
	Optional<Materia> findByCodigo(String codigo);
	List<Materia> findByNomeContaining(String nome);
	
}
