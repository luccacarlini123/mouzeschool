package com.mouzetech.mouzeschoolapi.mapper.disassembler;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.mouzetech.mouzeschoolapi.api.model.input.CadastrarMateriaInput;
import com.mouzetech.mouzeschoolapi.domain.model.Materia;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class MateriaModelDisassembler {

	private ModelMapper modelMapper;
	
	public Materia toEntity(CadastrarMateriaInput dto) {		
		return modelMapper.map(dto, Materia.class);
	}
}