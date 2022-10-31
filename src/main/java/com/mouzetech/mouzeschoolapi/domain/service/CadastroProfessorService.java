package com.mouzetech.mouzeschoolapi.domain.service;

import java.time.LocalDate;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mouzetech.mouzeschoolapi.api.model.input.CadastrarProfessorInput;
import com.mouzetech.mouzeschoolapi.api.model.input.EnderecoInput;
import com.mouzetech.mouzeschoolapi.domain.exception.NegocioException;
import com.mouzetech.mouzeschoolapi.domain.exception.ProfessorNaoEncontradoException;
import com.mouzetech.mouzeschoolapi.domain.model.Cidade;
import com.mouzetech.mouzeschoolapi.domain.model.Endereco;
import com.mouzetech.mouzeschoolapi.domain.model.Matricula;
import com.mouzetech.mouzeschoolapi.domain.model.Professor;
import com.mouzetech.mouzeschoolapi.domain.model.enumeration.StatusGeral;
import com.mouzetech.mouzeschoolapi.domain.repository.MatriculaRepository;
import com.mouzetech.mouzeschoolapi.domain.repository.ProfessorRepository;
import com.mouzetech.mouzeschoolapi.mapper.EnderecoModelMapper;
import com.mouzetech.mouzeschoolapi.mapper.ProfessorModelMapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CadastroProfessorService {

	private ProfessorRepository professorRepository;
	private MatriculaRepository matriculaRepository;
	private ProfessorModelMapper professorModelMapper;
	private EnderecoModelMapper enderecoModelMapper;
	private CadastroCidadeService cadastroCidadeService;
	private PessoaService pessoaService;
	
	public Professor buscarPorId(Long professorId) {
		return professorRepository.findById(professorId)
				.orElseThrow(() -> new ProfessorNaoEncontradoException(String.format("Não existe um professor com o id %d", professorId)));
	}
	
	@Transactional
	public Professor matricularProfessor(Professor professor) {
		
		if(professorRepository.existsByEmail(professor.getEmail())) {
			throw new NegocioException("Já existe um professor cadastrado com esse email");
		}
		
		if(pessoaService.cpfJaCadastrado(professor.getCpf())) {
			throw new NegocioException("Já existe alguém cadastrado com esse CPF");
		}
		
		Matricula matricula = new Matricula();
		matricula.setDataCadastro(LocalDate.now());
		matricula.setStatus(StatusGeral.ATIVADA);
		matricula = matriculaRepository.save(matricula);
		
		professor.setMatricula(matricula);
		
		professor = professorRepository.save(professor);
		
		matricula.setCodigoMatricula(
				String.valueOf(LocalDate.now().getYear()+""+professor.getId()+"66"+LocalDate.now().getMonthValue()+""+"0144"));
		
		return professor;
	}
	
	@Transactional
	public void atualizar(CadastrarProfessorInput dto, Long professorId) {
		Professor professor = buscarPorId(professorId);
		Matricula matricula = professor.getMatricula();
		
		if(professorRepository.existsByEmail(dto.getEmail()) && !(professor.getEmail().equals(dto.getEmail()))) {
			throw new NegocioException("Email já cadastrado");
		}
		
		if(professorRepository.existsByCpf(dto.getCpf()) && !(professor.getCpf().equals(dto.getCpf()))) {
			throw new NegocioException("CPF já cadastrado");
		}
		
		professor = professorModelMapper.toEntity(dto);
		professor.setId(professorId);
		professor.setMatricula(matricula);
		
		professorRepository.save(professor);
	}
	
	@Transactional
	public void excluir(Long professororId) {
		try {
			Professor professor = buscarPorId(professororId);
			if(professor.matriculaAtivada()) {
				throw new NegocioException("Não é possível excluir um professor que esteja com a matrícula ATIVADA.");
			}
			professorRepository.delete(professor);
			professorRepository.flush();
		}catch(DataIntegrityViolationException ex) {
			throw new NegocioException("Não é possível excluir um professor que esteja associado a uma turma");
		}
	}
	
	@Transactional
	public void ativarMatricula(Long alunoId) {
		Professor professor = buscarPorId(alunoId);
		professor.getMatricula().setStatus(StatusGeral.ATIVADA);
	}
	
	@Transactional
	public void desativarMatricula(Long alunoId) {
		Professor professor = buscarPorId(alunoId);
		professor.getMatricula().setStatus(StatusGeral.DESATIVADA);
	}
	
	@Transactional
	public void cadastrarEndereco(EnderecoInput input, Long professorId) {
		Professor professor = buscarPorId(professorId);
		Endereco endereco = enderecoModelMapper.toObject(input);
		Cidade cidade = cadastroCidadeService.buscarPorId(input.getCidadeId());
		endereco.setCidade(cidade);
		professor.setEndereco(endereco);
	}
	
}