package com.mouzetech.mouzeschoolapi.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mouzetech.mouzeschoolapi.domain.model.Aluno;
import com.mouzetech.mouzeschoolapi.domain.model.FotoAluno;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long>, CatalogoFotoAlunoQueries {
	boolean existsByEmail(String email);
	boolean existsByCpf(String cpf);
	List<Aluno> findByEmailContaining(String email);
	List<Aluno> findByNomeContaining(String nome);
	
	@Query(value = "select fa from FotoAluno fa "
			+ "join fa.aluno a "
			+ "where a.id = :alunoId")
	Optional<FotoAluno> buscarFotoAluno(Long alunoId);
	
	@Query(value = "select a from Aluno a where a.matricula.status = 'ATIVADA'")
	List<Aluno> buscarTodosAlunosComMatriculaAtiva();
	
	@Query(nativeQuery = true, value = "select aluno.* from alunos_turma "
			+ "	join aluno on aluno.id = alunos_turma.aluno_id "
			+ "	join turma on turma.id = alunos_turma.turma_id "
			+ "	where aluno.id = :alunoId and turma.status_turma = 'ATIVADA'")
	Optional<Aluno> buscarAlunoMatriculadoEmTurmaAtiva(Long alunoId);
	
	
	
}
