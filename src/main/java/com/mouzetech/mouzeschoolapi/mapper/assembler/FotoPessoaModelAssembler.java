package com.mouzetech.mouzeschoolapi.mapper.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mouzetech.mouzeschoolapi.api.model.output.FotoPessoaModel;
import com.mouzetech.mouzeschoolapi.domain.model.FotoAluno;
import com.mouzetech.mouzeschoolapi.domain.model.FotoProfessor;

@Component
public class FotoPessoaModelAssembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public FotoPessoaModel toModel(FotoAluno foto) {
		return modelMapper.map(foto, FotoPessoaModel.class);
	}
	
	public FotoPessoaModel toModel(FotoProfessor foto) {
		return modelMapper.map(foto, FotoPessoaModel.class);
	}
	
}