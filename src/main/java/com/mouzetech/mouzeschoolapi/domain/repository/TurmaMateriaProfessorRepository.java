package com.mouzetech.mouzeschoolapi.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mouzetech.mouzeschoolapi.domain.model.Materia;
import com.mouzetech.mouzeschoolapi.domain.model.Turma;
import com.mouzetech.mouzeschoolapi.domain.model.TurmaMateriaProfessor;
import com.mouzetech.mouzeschoolapi.domain.model.TurmaMateriaProfessorId;

@Repository
public interface TurmaMateriaProfessorRepository extends JpaRepository<TurmaMateriaProfessor, TurmaMateriaProfessorId> {
	
	@Query(value = "SELECT count(*) from turma_materia_professor tmp"
			+ "	where tmp.materia_id = :materiaId and tmp.turma_id = :turmaId and tmp.professor_id is not null", nativeQuery = true)
		int existeProfessorAlocadoNaMateriaDaTurma(Long materiaId, Long turmaId);
	
	@Query(value = "SELECT count(*) from turma_materia_professor tmp"
			+ "	where tmp.materia_id = :materiaId and tmp.turma_id = :turmaId", nativeQuery = true)
		int existeMateriaAssociadaNaTurma(Long materiaId, Long turmaId);
	
	@Query("SELECT DISTINCT tmp.turmaMateriaProfessorId.materia from TurmaMateriaProfessor tmp "
			+ "WHERE tmp.turmaMateriaProfessorId.turma.id = :turmaId ORDER BY tmp.turmaMateriaProfessorId.materia.nome ASC")
	List<Materia> buscarMateriasDaTurma(Long turmaId);

	@Query("from TurmaMateriaProfessor tmp where tmp.turmaMateriaProfessorId.turma = :turma")
	List<TurmaMateriaProfessor> buscarGradeCurricularPorTurma(Turma turma);
	
	@Query("from TurmaMateriaProfessor tmp where tmp.turmaMateriaProfessorId.materia = :materia")
	List<TurmaMateriaProfessor> buscarTurmasMateriaAssociada(Materia materia);
	
}   