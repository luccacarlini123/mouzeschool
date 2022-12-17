package com.mouzetech.mouzeschoolapi.mapper.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.mouzetech.mouzeschoolapi.api.model.output.AlunoApenasNomeModel;
import com.mouzetech.mouzeschoolapi.domain.model.Aluno;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class AlunoApenasNomeModelAssembler {

	private ModelMapper modelMapper;
	
	public AlunoApenasNomeModel toModel(Aluno aluno) {
		AlunoApenasNomeModel dto = new AlunoApenasNomeModel();
		dto.setNomeAluno(aluno.getNome());
		return dto;
	}
	
	public List<AlunoApenasNomeModel> toCollectionModel(List<Aluno> alunos) {
		return alunos.stream().map(aluno -> toModel(aluno))
				.collect(Collectors.toList());
	}
}