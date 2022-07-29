package com.mouzetech.mouzeschoolapi.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mouzetech.mouzeschoolapi.domain.model.Turma;
import com.mouzetech.mouzeschoolapi.domain.model.enumeration.GrauEnsino;
import com.mouzetech.mouzeschoolapi.domain.model.enumeration.SerieEnsino;
import com.mouzetech.mouzeschoolapi.domain.model.enumeration.StatusGeral;

@Repository
public interface TurmaRepository extends JpaRepository<Turma, Long> {

//	@Query("from Turma t left join fetch t.alunos left join fetch t.professores")
//	List<Turma> findAll();
	
	@Query(value = "select count(*) from alunos_turma alt "
			+ "join turma t on alt.turma_id = t.id "
			+ "join  aluno a on alt.aluno_id = a.id "
			+ "where t.status_turma != 'DESATIVADA' and a.id = :alunoId", nativeQuery = true)
	int quantidadeMatriculaPorAlunoEmTurmaAtivada(Long alunoId);

	Optional<Turma> findByNome(String nome);
	
	List<Turma> findByStatusTurma(StatusGeral statusTurma);
	
	Optional<Turma> findByNomeAndGrauEnsinoAndSerieEnsino(String nome, GrauEnsino grauEnsino, SerieEnsino serieEnsino);
}