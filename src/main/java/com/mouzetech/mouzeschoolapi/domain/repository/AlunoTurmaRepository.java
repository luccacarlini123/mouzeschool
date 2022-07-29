package com.mouzetech.mouzeschoolapi.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mouzetech.mouzeschoolapi.domain.model.Aluno;

@Repository
public interface AlunoTurmaRepository extends JpaRepository<Aluno, Long> {
	boolean existsByEmail(String email);
	List<Aluno> findByEmailContaining(String email);
	List<Aluno> findByNomeContaining(String nome);
}
