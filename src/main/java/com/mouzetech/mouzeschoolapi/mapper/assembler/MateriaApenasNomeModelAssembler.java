package com.mouzetech.mouzeschoolapi.mapper.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.mouzetech.mouzeschoolapi.api.model.output.MateriaApenasNomeModel;
import com.mouzetech.mouzeschoolapi.domain.model.Materia;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class MateriaApenasNomeModelAssembler {

	private ModelMapper modelMapper;
	
	public MateriaApenasNomeModel toModel(Materia materia) {
		return modelMapper.map(materia, MateriaApenasNomeModel.class);
	}
}