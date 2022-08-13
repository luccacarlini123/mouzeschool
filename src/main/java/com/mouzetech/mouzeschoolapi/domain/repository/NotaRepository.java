package com.mouzetech.mouzeschoolapi.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mouzetech.mouzeschoolapi.domain.model.Aluno;
import com.mouzetech.mouzeschoolapi.domain.model.Materia;
import com.mouzetech.mouzeschoolapi.domain.model.Nota;
import com.mouzetech.mouzeschoolapi.domain.model.Turma;

@Repository
public interface NotaRepository extends JpaRepository<Nota, Long> {	
	
	@Query(value = "SELECT COUNT(*) FROM nota WHERE bimestre = :bimestre "
			+ "and aluno_id = :alunoId and turma_id = :turmaId and materia_id = :materiaId", 
			nativeQuery = true)
	int existeNotaNoBimestre(Long alunoId, Long turmaId, Long materiaId, Short bimestre);
	
	List<Nota> findByAlunoAndTurma(Aluno aluno, Turma turma);
	List<Nota> findByAlunoAndTurmaAndMateria(Aluno aluno, Turma turma, Materia materia);
	List<Nota> findByTurma(Turma turma);
	
}
