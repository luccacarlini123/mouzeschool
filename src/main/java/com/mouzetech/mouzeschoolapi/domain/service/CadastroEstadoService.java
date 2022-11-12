package com.mouzetech.mouzeschoolapi.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.mouzetech.mouzeschoolapi.api.model.input.EstadoInput;
import com.mouzetech.mouzeschoolapi.domain.exception.EstadoNaoEncontradoException;
import com.mouzetech.mouzeschoolapi.domain.exception.NegocioException;
import com.mouzetech.mouzeschoolapi.domain.model.Estado;
import com.mouzetech.mouzeschoolapi.domain.repository.EstadoRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CadastroEstadoService {

	private EstadoRepository estadoRepository;
	
	public List<Estado> buscarTodos(){
		return estadoRepository.findAll();
	}
	
	public Estado buscarPorId(Long estadoId) {
		
		Optional<Estado> optionalEstado = estadoRepository.findById(estadoId);
		
		if(optionalEstado.isEmpty()) {
			throw new EstadoNaoEncontradoException("Não existe nenhum estado com o id: " + estadoId);
		}
		
		return optionalEstado.get();
	}
	
	public Estado salvar(Estado estado) {
		return estadoRepository.save(estado);
	}
	
	public void atualizar(EstadoInput estadoInput, Long estadoId) {
		Estado estado = buscarPorId(estadoId);
		estado.setNome(estadoInput.getNome());
		estado.setSigla(estadoInput.getSigla());
		salvar(estado);
	}
	
	public void excluir(Long estadoId) {
		try {
			Estado estado = buscarPorId(estadoId);
			estadoRepository.delete(estado);
			estadoRepository.flush();
		} catch(DataIntegrityViolationException ex) {	
			throw new NegocioException("O estado está associado a 1 ou mais cidades, impossível excluir");
		}
	}
	
	
	
}
