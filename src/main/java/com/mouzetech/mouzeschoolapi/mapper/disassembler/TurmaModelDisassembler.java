package com.mouzetech.mouzeschoolapi.mapper.disassembler;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.mouzetech.mouzeschoolapi.api.model.input.TurmaInput;
import com.mouzetech.mouzeschoolapi.api.model.output.TurmaModel;
import com.mouzetech.mouzeschoolapi.domain.model.Turma;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class TurmaModelDisassembler {

	private ModelMapper modelMapper;
	
	public Turma toEntity(TurmaInput dto) {
		return modelMapper.map(dto, Turma.class);
	}
	
	public TurmaModel toModel(Turma turma) {
		return modelMapper.map(turma, TurmaModel.class);
	}
}