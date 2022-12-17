package com.mouzetech.mouzeschoolapi.mapper.disassembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mouzetech.mouzeschoolapi.api.model.input.EnderecoInput;
import com.mouzetech.mouzeschoolapi.domain.model.Endereco;

@Service
public class EnderecoModelDisassembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public Endereco toEntity(EnderecoInput endereco) {
		return modelMapper.map(endereco, Endereco.class);
	}
}