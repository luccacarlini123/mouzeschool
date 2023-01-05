package com.mouzetech.mouzeschoolapi.mapper.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.mouzetech.mouzeschoolapi.api.ApiLinkBuilder;
import com.mouzetech.mouzeschoolapi.api.controller.CidadeController;
import com.mouzetech.mouzeschoolapi.api.model.output.CidadeResumoModel;
import com.mouzetech.mouzeschoolapi.domain.model.Cidade;

@Component
public class CidadeResumoModelAssembler extends RepresentationModelAssemblerSupport<Cidade, CidadeResumoModel> {

	public CidadeResumoModelAssembler() {
		super(CidadeController.class, CidadeResumoModel.class);
	}

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private ApiLinkBuilder apiLinkBuilder;
	
	public CidadeResumoModel toModel(Cidade cidade) {
		CidadeResumoModel cidadeResumoModel = createModelWithId(cidade.getId(), cidade);
		cidadeResumoModel.setId(cidade.getId());
		cidadeResumoModel.setNome(cidade.getNome());
		cidadeResumoModel.setEstado(cidade.getEstado().getNome());
		
		return cidadeResumoModel;
	}
	
	@Override
	public CollectionModel<CidadeResumoModel> toCollectionModel(Iterable<? extends Cidade> entities) {
		CollectionModel<CidadeResumoModel> collectionCidadeResumoModel = super.toCollectionModel(entities);
		
		collectionCidadeResumoModel.add(apiLinkBuilder.linkToCidades(IanaLinkRelations.SELF.toString()));
		
		return collectionCidadeResumoModel;
	}
	
}