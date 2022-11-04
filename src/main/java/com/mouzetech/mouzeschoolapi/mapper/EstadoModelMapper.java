package com.mouzetech.mouzeschoolapi.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.mouzetech.mouzeschoolapi.api.model.input.EstadoInput;
import com.mouzetech.mouzeschoolapi.api.model.output.EstadoModel;
import com.mouzetech.mouzeschoolapi.api.model.output.ResumoEstadoModel;
import com.mouzetech.mouzeschoolapi.domain.model.Estado;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class EstadoModelMapper {

	private ModelMapper modelMapper;
	
	public EstadoModel toEstadoModel(Estado estado) {
		return modelMapper.map(estado, EstadoModel.class);
	}
	
	public List<EstadoModel> toCollectionEstadoModel(List<Estado> estados){
		return estados.stream()
				.map(estado -> toEstadoModel(estado))
				.collect(Collectors.toList());
	}
	
	public ResumoEstadoModel toResumoEstadoModel(Estado estado) {
		return modelMapper.map(estado, ResumoEstadoModel.class);
	}
	
	public List<ResumoEstadoModel> toCollectionResumoEstadoModel(List<Estado> estados){
		return estados.stream()
				.map(estado -> toResumoEstadoModel(estado))
				.collect(Collectors.toList());
	}
	
	public Estado toEntity(EstadoInput dto) {
		return modelMapper.map(dto, Estado.class);
	}
}