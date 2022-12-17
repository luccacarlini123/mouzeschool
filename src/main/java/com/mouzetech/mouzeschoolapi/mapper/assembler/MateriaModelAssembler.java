package com.mouzetech.mouzeschoolapi.mapper.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.mouzetech.mouzeschoolapi.api.model.output.MateriaModel;
import com.mouzetech.mouzeschoolapi.domain.model.Materia;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class MateriaModelAssembler {

	private ModelMapper modelMapper;
	
	public MateriaModel toModel(Materia materia) {
		return modelMapper.map(materia, MateriaModel.class);
	}
	
	public List<MateriaModel> toCollectionModel(List<Materia> materias) {
		return materias.stream()
				.map(materia -> toModel(materia))
				.collect(Collectors.toList());
	}
}