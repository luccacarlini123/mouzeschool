package com.mouzetech.mouzeschoolapi.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.mouzetech.mouzeschoolapi.api.model.input.CadastrarProfessorInput;
import com.mouzetech.mouzeschoolapi.api.model.output.ProfessorModel;
import com.mouzetech.mouzeschoolapi.api.model.output.ResumoProfessorModel;
import com.mouzetech.mouzeschoolapi.domain.model.Professor;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class ProfessorModelMapper {

	private ModelMapper modelMapper;
	
	public ProfessorModel toProfessorDTO(Professor professor) {
		return modelMapper.map(professor, ProfessorModel.class);
	}
	
	public List<ProfessorModel> toCollectionProfessorDTO(List<Professor> professores) {
		return professores.stream()
					.map(professor -> toProfessorDTO(professor))
					.collect(Collectors.toList());
	}
	
	public ResumoProfessorModel toResumoProfessorDTO(Professor professor) {
		return modelMapper.map(professor, ResumoProfessorModel.class);
	}
	
	public List<ResumoProfessorModel> toCollectionResumoProfessorDTO(List<Professor> professores) {
		return professores.stream()
					.map(professor -> toResumoProfessorDTO(professor))
					.collect(Collectors.toList());
	}
	
	public Professor toEntity(CadastrarProfessorInput dto) {
		return modelMapper.map(dto, Professor.class);
	}
}