package com.mouzetech.mouzeschoolapi.mapper.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.mouzetech.mouzeschoolapi.api.model.output.NotaModel;
import com.mouzetech.mouzeschoolapi.domain.model.Nota;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class NotaModelAssembler {

	private ModelMapper modelMapper;
	
	public NotaModel toModel(Nota nota) {
		return modelMapper.map(nota, NotaModel.class);
	}
	
	public List<NotaModel> toCollectionModel(List<Nota> notas){
		return notas.stream()
					.map(nota -> toModel(nota))
					.collect(Collectors.toList());
	}
}