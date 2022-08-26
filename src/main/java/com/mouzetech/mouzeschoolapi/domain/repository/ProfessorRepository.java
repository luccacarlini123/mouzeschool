package com.mouzetech.mouzeschoolapi.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mouzetech.mouzeschoolapi.domain.model.FotoProfessor;
import com.mouzetech.mouzeschoolapi.domain.model.Professor;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long>, CatalogoFotoProfessorQueries {

	boolean existsByEmail(String email);
	boolean existsByCpf(String cpf);
	List<Professor> findByEmailContaining(String email);
	List<Professor> findByNomeContaining(String nome);
	
	@Query(value = "SELECT new Professor(p.id, p.nome, p.email) FROM Professor p")
	List<Professor> buscarProfessoresComDadosResumidos();
	
	@Query("select fp from FotoProfessor fp "
			+ "join fp.professor p "
			+ "where p.id = :professorId")
	Optional<FotoProfessor> buscarFotoDoProfessor(Long professorId);

}
