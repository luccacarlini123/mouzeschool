package com.mouzetech.mouzeschoolapi.mapper.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.mouzetech.mouzeschoolapi.api.model.output.TurmaModel;
import com.mouzetech.mouzeschoolapi.domain.model.Turma;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class TurmaModelAssembler {

	private ModelMapper modelMapper;
	
	public TurmaModel toModel(Turma turma) {
		return modelMapper.map(turma, TurmaModel.class);
	}
}