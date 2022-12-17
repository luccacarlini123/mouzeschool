package com.mouzetech.mouzeschoolapi.mapper.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.mouzetech.mouzeschoolapi.api.model.output.ResumoTurmaModel;
import com.mouzetech.mouzeschoolapi.domain.model.Turma;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class ResumoTurmaModelAssembler {

	private ModelMapper modelMapper;
	
	public ResumoTurmaModel toModel(Turma turma) {
		return modelMapper.map(turma, ResumoTurmaModel.class);
	}
	
	public List<ResumoTurmaModel> toCollectionModel(List<Turma> turmas){
		return turmas.stream()
				.map(turma -> toModel(turma))	
				.collect(Collectors.toList());
	}
}