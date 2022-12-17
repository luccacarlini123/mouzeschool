package com.mouzetech.mouzeschoolapi.mapper.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.mouzetech.mouzeschoolapi.api.model.output.AlunoModel;
import com.mouzetech.mouzeschoolapi.domain.model.Aluno;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class AlunoModelAssembler {

	private ModelMapper modelMapper;
	
	public AlunoModel toAlunoModel(Aluno aluno) {
		return modelMapper.map(aluno, AlunoModel.class);
	}
	
	public List<AlunoModel> toCollectionAlunoModel(List<Aluno> alunos){
		return alunos.stream()
				.map(aluno -> toAlunoModel(aluno))
				.collect(Collectors.toList());
	}
}