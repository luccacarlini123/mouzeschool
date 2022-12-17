package com.mouzetech.mouzeschoolapi.mapper.disassembler;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.mouzetech.mouzeschoolapi.api.model.input.CidadeInput;
import com.mouzetech.mouzeschoolapi.domain.model.Cidade;
import com.mouzetech.mouzeschoolapi.domain.model.Estado;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class CidadeModelDisassembler {

	private ModelMapper modelMapper;
	
	public Cidade toEntity(CidadeInput input) {
		Cidade cidade = new Cidade();
		cidade.setNome(input.getNome());
		cidade.setEstado(new Estado());
		cidade.getEstado().setId(input.getEstadoId());
		return cidade;
	}
}