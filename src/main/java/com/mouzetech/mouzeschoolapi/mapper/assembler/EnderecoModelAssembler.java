package com.mouzetech.mouzeschoolapi.mapper.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mouzetech.mouzeschoolapi.api.model.output.EnderecoModel;
import com.mouzetech.mouzeschoolapi.domain.model.Endereco;

@Service
public class EnderecoModelAssembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public EnderecoModel toModel(Endereco endereco) {
		return modelMapper.map(endereco, EnderecoModel.class);
	}	
}