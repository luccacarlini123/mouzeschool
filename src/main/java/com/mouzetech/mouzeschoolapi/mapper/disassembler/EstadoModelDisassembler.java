package com.mouzetech.mouzeschoolapi.mapper.disassembler;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.mouzetech.mouzeschoolapi.api.model.input.EstadoInput;
import com.mouzetech.mouzeschoolapi.domain.model.Estado;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class EstadoModelDisassembler {

	private ModelMapper modelMapper;
	
	public Estado toEntity(EstadoInput dto) {
		return modelMapper.map(dto, Estado.class);
	}
}