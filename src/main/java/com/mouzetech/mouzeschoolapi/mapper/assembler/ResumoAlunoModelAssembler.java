package com.mouzetech.mouzeschoolapi.mapper.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.mouzetech.mouzeschoolapi.api.model.output.ResumoAlunoModel;
import com.mouzetech.mouzeschoolapi.domain.model.Aluno;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class ResumoAlunoModelAssembler {

	private ModelMapper modelMapper;
	
	public ResumoAlunoModel toModel(Aluno aluno) {
		return modelMapper.map(aluno, ResumoAlunoModel.class);
	}
	
	public List<ResumoAlunoModel> toCollectionModel(List<Aluno> alunos){
		return alunos.stream()
				.map(aluno -> toModel(aluno))
				.collect(Collectors.toList());
	}
}