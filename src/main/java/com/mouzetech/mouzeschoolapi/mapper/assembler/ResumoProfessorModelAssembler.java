package com.mouzetech.mouzeschoolapi.mapper.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.mouzetech.mouzeschoolapi.api.model.output.ResumoProfessorModel;
import com.mouzetech.mouzeschoolapi.domain.model.Professor;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class ResumoProfessorModelAssembler {

	private ModelMapper modelMapper;
	
	public ResumoProfessorModel toModel(Professor professor) {
		return modelMapper.map(professor, ResumoProfessorModel.class);
	}
	
	public List<ResumoProfessorModel> toCollectionModel(List<Professor> professores) {
		return professores.stream()
					.map(professor -> toModel(professor))
					.collect(Collectors.toList());
	}
}