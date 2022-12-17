package com.mouzetech.mouzeschoolapi.mapper.disassembler;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.mouzetech.mouzeschoolapi.api.model.input.CadastrarProfessorInput;
import com.mouzetech.mouzeschoolapi.domain.model.Professor;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class ProfessorModelDisassembler {

	private ModelMapper modelMapper;
	
	public Professor toEntity(CadastrarProfessorInput dto) {
		return modelMapper.map(dto, Professor.class);
	}
}