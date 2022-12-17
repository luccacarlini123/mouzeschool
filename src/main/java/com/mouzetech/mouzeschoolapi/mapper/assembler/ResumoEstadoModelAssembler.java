package com.mouzetech.mouzeschoolapi.mapper.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.mouzetech.mouzeschoolapi.api.model.output.ResumoEstadoModel;
import com.mouzetech.mouzeschoolapi.domain.model.Estado;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class ResumoEstadoModelAssembler {

	private ModelMapper modelMapper;
	
	public ResumoEstadoModel toModel(Estado estado) {
		return modelMapper.map(estado, ResumoEstadoModel.class);
	}
	
	public List<ResumoEstadoModel> toCollectionModel(List<Estado> estados){
		return estados.stream()
				.map(estado -> toModel(estado))
				.collect(Collectors.toList());
	}
}