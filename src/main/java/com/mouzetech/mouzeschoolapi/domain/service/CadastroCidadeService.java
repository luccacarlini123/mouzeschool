package com.mouzetech.mouzeschoolapi.domain.service;


import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mouzetech.mouzeschoolapi.api.model.input.CidadeInput;
import com.mouzetech.mouzeschoolapi.domain.exception.AlunoNaoEncontradoException;
import com.mouzetech.mouzeschoolapi.domain.exception.NegocioException;
import com.mouzetech.mouzeschoolapi.domain.model.Cidade;
import com.mouzetech.mouzeschoolapi.domain.model.Estado;
import com.mouzetech.mouzeschoolapi.domain.repository.CidadeRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CadastroCidadeService {

	private CidadeRepository cidadeRepository;
	private CadastroEstadoService cadastroEstadoService;
	
	public List<Cidade> buscarTodos(){
		return cidadeRepository.findAll();
	}
	
	public Cidade buscarPorId(Long alunoId) {
		return cidadeRepository.findById(alunoId)
					.orElseThrow(() -> new AlunoNaoEncontradoException(String.format("Não existe cidade com o id %d", alunoId)));
	}
	
	@Transactional
	public Cidade salvar(Cidade cidade){
		Estado estado = cadastroEstadoService.buscarPorId(cidade.getEstado().getId());
		cidade.setEstado(estado);
		return cidadeRepository.save(cidade);
	}
	
	@Transactional
	public void atualizar(Long cidadeId, CidadeInput input) {
		Cidade cidade = buscarPorId(cidadeId);
		
		Estado estado = cadastroEstadoService.buscarPorId(input.getEstadoId());
		
		cidade.setNome(input.getNome());
		cidade.setEstado(estado);
	}
	
	@Transactional
	public void excluir(Long cidadeId) {
		try {
			Cidade cidade = buscarPorId(cidadeId);
			cidadeRepository.delete(cidade);
			cidadeRepository.flush();
		} catch(DataIntegrityViolationException ex) {
			throw new NegocioException("A cidade está associada a 1 ou mais endereços, impossível excluir");
		}
	}
}