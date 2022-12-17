package com.mouzetech.mouzeschoolapi.mapper.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.mouzetech.mouzeschoolapi.api.model.output.ProfessorModel;
import com.mouzetech.mouzeschoolapi.domain.model.Professor;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class ProfessorModelAssembler {

	private ModelMapper modelMapper;
	
	public ProfessorModel toModel(Professor professor) {
		return modelMapper.map(professor, ProfessorModel.class);
	}
	
	public List<ProfessorModel> toCollectionModel(List<Professor> professores) {
		return professores.stream()
					.map(professor -> toModel(professor))
					.collect(Collectors.toList());
	}
}