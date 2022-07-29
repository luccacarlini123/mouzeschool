package com.mouzetech.mouzeschoolapi.domain.service;

import java.time.LocalDate;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mouzetech.mouzeschoolapi.api.model.input.CadastrarAlunoInput;
import com.mouzetech.mouzeschoolapi.api.model.input.EnderecoInput;
import com.mouzetech.mouzeschoolapi.domain.exception.AlunoNaoEncontradoException;
import com.mouzetech.mouzeschoolapi.domain.exception.NegocioException;
import com.mouzetech.mouzeschoolapi.domain.model.Aluno;
import com.mouzetech.mouzeschoolapi.domain.model.Cidade;
import com.mouzetech.mouzeschoolapi.domain.model.Endereco;
import com.mouzetech.mouzeschoolapi.domain.model.Matricula;
import com.mouzetech.mouzeschoolapi.domain.model.enumeration.StatusGeral;
import com.mouzetech.mouzeschoolapi.domain.repository.AlunoRepository;
import com.mouzetech.mouzeschoolapi.domain.repository.MatriculaRepository;
import com.mouzetech.mouzeschoolapi.mapper.AlunoModelMapper;
import com.mouzetech.mouzeschoolapi.mapper.EnderecoModelMapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CadastroAlunoService {

	private AlunoRepository alunoRepository;
	private MatriculaRepository matriculaRepository;
	private AlunoModelMapper alunoModelMapper;
	private EnderecoModelMapper enderecoModelMapper;
	private CadastroCidadeService cadastroCidadeService;
	private PessoaService pessoaService;
	
	public Aluno buscarPorId(Long alunoId) {
		return alunoRepository.findById(alunoId)
					.orElseThrow(() -> new AlunoNaoEncontradoException(String.format("Não existe um aluno com o id %d", alunoId)));
	}
	
	@Transactional
	public Aluno matricularAluno(Aluno aluno) {
		if(alunoRepository.existsByEmail(aluno.getEmail())) {
			throw new NegocioException("Já existe um aluno cadastrado com esse email");
		}
		
		if(pessoaService.cpfJaCadastrado(aluno.getCpf())) {
			throw new NegocioException("Já existe alguém cadastrado com esse CPF");
		}
		
		Matricula matricula = new Matricula();
		matricula.setDataCadastro(LocalDate.now());
		matricula.setStatus(StatusGeral.ATIVADA);
		matricula = matriculaRepository.save(matricula);
		
		aluno.setMatricula(matricula);
		
		aluno = alunoRepository.save(aluno);
		
		matricula.setCodigoMatricula(
				String.valueOf(LocalDate.now().getYear()+""+aluno.getId()+"77"+LocalDate.now().getMonthValue()+""+"0133"));
		
		return aluno;
	}
	
	@Transactional
	public void atualizar(CadastrarAlunoInput dto, Long alunoId) {
		Aluno aluno = buscarPorId(alunoId);
		Matricula matricula = aluno.getMatricula();
		
		if(alunoRepository.existsByEmail(dto.getEmail()) && !(aluno.getEmail().equals(dto.getEmail()))) {
			throw new NegocioException("Email já cadastrado");
		}
		
		aluno = alunoModelMapper.toEntity(dto);
		aluno.setId(alunoId);
		aluno.setMatricula(matricula);
		
		alunoRepository.save(aluno);
	}
	
	@Transactional
	public void excluir(Long alunoId) {
		try {
			Aluno aluno = buscarPorId(alunoId);
			alunoRepository.delete(aluno);
			alunoRepository.flush();
		}catch(DataIntegrityViolationException ex) {
			throw new NegocioException("Não é possível excluir um aluno que esteja associado a uma turma");
		}
	}
	
	@Transactional
	public void cadastrarEndereco(EnderecoInput input, Long alunoId) {
		Aluno aluno = buscarPorId(alunoId);
		Endereco endereco = enderecoModelMapper.toObject(input);
		Cidade cidade = cadastroCidadeService.buscarPorId(input.getCidadeId());
		endereco.setCidade(cidade);
		aluno.setEndereco(endereco);
	}
	
	@Transactional
	public void ativarMatricula(Long alunoId) {
		Aluno aluno = buscarPorId(alunoId);
		aluno.getMatricula().setStatus(StatusGeral.ATIVADA);
	}
	
	@Transactional
	public void desativarMatricula(Long alunoId) {
		Aluno aluno = buscarPorId(alunoId);
		aluno.getMatricula().setStatus(StatusGeral.DESATIVADA);
	}
}