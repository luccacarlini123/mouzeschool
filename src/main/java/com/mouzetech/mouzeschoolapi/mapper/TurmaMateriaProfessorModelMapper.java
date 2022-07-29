package com.mouzetech.mouzeschoolapi.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.mouzetech.mouzeschoolapi.api.model.output.GradeCurricularModel;
import com.mouzetech.mouzeschoolapi.domain.model.TurmaMateriaProfessor;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class TurmaMateriaProfessorModelMapper {

	private ModelMapper modelMapper;
	
	public GradeCurricularModel toGardeCurricularDTO(TurmaMateriaProfessor turmaMateriaProfessor) {
		return modelMapper.map(turmaMateriaProfessor, GradeCurricularModel.class);
	}
	
	public List<GradeCurricularModel> toCollectionDTO(List<TurmaMateriaProfessor> turmaMateriaProfessorList) {
		return turmaMateriaProfessorList
				.stream()
				.map(turmaMateriaProfessor -> toGardeCurricularDTO(turmaMateriaProfessor))
				.collect(Collectors.toList());
	}
}