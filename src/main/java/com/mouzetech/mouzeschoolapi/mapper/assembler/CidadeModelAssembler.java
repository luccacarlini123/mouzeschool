package com.mouzetech.mouzeschoolapi.mapper.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.mouzetech.mouzeschoolapi.api.ApiLinkBuilder;
import com.mouzetech.mouzeschoolapi.api.controller.CidadeController;
import com.mouzetech.mouzeschoolapi.api.model.output.CidadeModel;
import com.mouzetech.mouzeschoolapi.domain.model.Cidade;

@Component
public class CidadeModelAssembler extends RepresentationModelAssemblerSupport<Cidade, CidadeModel> {

	public CidadeModelAssembler() {
		super(CidadeController.class, CidadeModel.class);
	}

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private ApiLinkBuilder apiLinkBuilder;
	
	public CidadeModel toModel(Cidade cidade) {
		CidadeModel cidadeModel = createModelWithId(cidade.getId(), cidade);
		modelMapper.map(cidade, cidadeModel);
		
		cidadeModel.getEstado().add(apiLinkBuilder.linkToEstado(cidadeModel.getEstado().getId(), "estado"));
		cidadeModel.add(apiLinkBuilder.linkToCidades("cidades"));
		
		return cidadeModel;
	}
}