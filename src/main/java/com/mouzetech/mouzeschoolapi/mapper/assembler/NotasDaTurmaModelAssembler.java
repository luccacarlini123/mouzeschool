package com.mouzetech.mouzeschoolapi.mapper.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.mouzetech.mouzeschoolapi.api.model.output.NotasDaTurmaModel;
import com.mouzetech.mouzeschoolapi.domain.model.Nota;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class NotasDaTurmaModelAssembler {

	private ModelMapper modelMapper;
	
	public NotasDaTurmaModel toModel(Nota nota) {
		return modelMapper.map(nota, NotasDaTurmaModel.class);
	}
	
	public List<NotasDaTurmaModel> toCollectionModel(List<Nota> notas){
		return notas.stream()
					.map(nota -> toModel(nota))
					.collect(Collectors.toList());
	}
	
}