package com.mouzetech.mouzeschoolapi.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mouzetech.mouzeschoolapi.api.model.input.EnderecoInput;
import com.mouzetech.mouzeschoolapi.api.model.output.EnderecoModel;
import com.mouzetech.mouzeschoolapi.domain.model.Endereco;

@Service
public class EnderecoModelMapper {

	@Autowired
	private ModelMapper modelMapper;
	
	public Endereco toObject(EnderecoInput endereco) {
		return modelMapper.map(endereco, Endereco.class);
	}
	
	public EnderecoModel toEnderecoModel(Endereco endereco) {
		return modelMapper.map(endereco, EnderecoModel.class);
	}	
}