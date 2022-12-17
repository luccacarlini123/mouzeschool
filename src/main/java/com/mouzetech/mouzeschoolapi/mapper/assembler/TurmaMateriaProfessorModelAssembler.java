package com.mouzetech.mouzeschoolapi.mapper.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.mouzetech.mouzeschoolapi.api.model.output.GradeCurricularModel;
import com.mouzetech.mouzeschoolapi.domain.model.TurmaMateriaProfessor;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class TurmaMateriaProfessorModelAssembler {

	private ModelMapper modelMapper;
	
	public GradeCurricularModel toModel(TurmaMateriaProfessor turmaMateriaProfessor) {
		return modelMapper.map(turmaMateriaProfessor, GradeCurricularModel.class);
	}
	
	public List<GradeCurricularModel> toCollectionModel(List<TurmaMateriaProfessor> turmaMateriaProfessorList) {
		return turmaMateriaProfessorList
				.stream()
				.map(turmaMateriaProfessor -> toModel(turmaMateriaProfessor))
				.collect(Collectors.toList());
	}
}