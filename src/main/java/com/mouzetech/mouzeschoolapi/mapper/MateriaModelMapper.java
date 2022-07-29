package com.mouzetech.mouzeschoolapi.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.mouzetech.mouzeschoolapi.api.model.input.CadastrarMateriaInput;
import com.mouzetech.mouzeschoolapi.api.model.output.MateriaModel;
import com.mouzetech.mouzeschoolapi.api.model.output.ResumoMateriaModel;
import com.mouzetech.mouzeschoolapi.domain.model.Materia;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class MateriaModelMapper {

	private ModelMapper modelMapper;
	
	public Materia toEntity(CadastrarMateriaInput dto) {		
		return modelMapper.map(dto, Materia.class);
	}
	
	public MateriaModel toModel(Materia materia) {
		return modelMapper.map(materia, MateriaModel.class);
	}
	
	public List<MateriaModel> toCollectionModel(List<Materia> materias) {
		return materias.stream()
				.map(materia -> toModel(materia))
				.collect(Collectors.toList());
	}
	
	public ResumoMateriaModel toResumoModel(Materia materia) {
		return modelMapper.map(materia, ResumoMateriaModel.class);
	}	
	
	public List<ResumoMateriaModel> toCollectionResumoModel(List<Materia> materias) {
		return materias.stream()
				.map(materia -> toResumoModel(materia))
				.collect(Collectors.toList());
	}
}