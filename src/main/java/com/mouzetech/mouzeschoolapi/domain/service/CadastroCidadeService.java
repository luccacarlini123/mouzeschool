package com.mouzetech.mouzeschoolapi.domain.service;

import org.springframework.stereotype.Service;

import com.mouzetech.mouzeschoolapi.domain.exception.AlunoNaoEncontradoException;
import com.mouzetech.mouzeschoolapi.domain.model.Cidade;
import com.mouzetech.mouzeschoolapi.domain.repository.CidadeRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CadastroCidadeService {

	private CidadeRepository cidadeRepository;
	
	public Cidade buscarPorId(Long alunoId) {
		return cidadeRepository.findById(alunoId)
					.orElseThrow(() -> new AlunoNaoEncontradoException(String.format("Não existe cidade com o id %d", alunoId)));
	}
}