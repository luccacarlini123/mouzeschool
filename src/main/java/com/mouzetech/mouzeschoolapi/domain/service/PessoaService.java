package com.mouzetech.mouzeschoolapi.domain.service;

import org.springframework.stereotype.Service;

import com.mouzetech.mouzeschoolapi.domain.repository.AlunoRepository;
import com.mouzetech.mouzeschoolapi.domain.repository.ProfessorRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PessoaService {

	private ProfessorRepository professorRepository;
	private AlunoRepository alunoRepository;
	
	public boolean cpfJaCadastrado(String cpf) {
		return professorRepository.existsByCpf(cpf)
				|| alunoRepository.existsByCpf(cpf);
	}
	
}
