package com.mouzetech.mouzeschoolapi.mapper.disassembler;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.mouzetech.mouzeschoolapi.api.model.input.AlunoInput;
import com.mouzetech.mouzeschoolapi.domain.model.Aluno;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class AlunoModelDisassembler {

	private ModelMapper modelMapper;
	
	public Aluno toEntity(AlunoInput dto) {
		return modelMapper.map(dto, Aluno.class);
	}
}