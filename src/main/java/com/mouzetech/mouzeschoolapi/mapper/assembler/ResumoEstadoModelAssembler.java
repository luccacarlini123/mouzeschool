package com.mouzetech.mouzeschoolapi.mapper.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.mouzetech.mouzeschoolapi.api.ApiLinkBuilder;
import com.mouzetech.mouzeschoolapi.api.controller.EstadoController;
import com.mouzetech.mouzeschoolapi.api.model.output.ResumoEstadoModel;
import com.mouzetech.mouzeschoolapi.domain.model.Estado;

@Component
public class ResumoEstadoModelAssembler extends RepresentationModelAssemblerSupport<Estado, ResumoEstadoModel> {

	public ResumoEstadoModelAssembler() {
		super(EstadoController.class, ResumoEstadoModel.class);
	}

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private ApiLinkBuilder apiLinkBuilder;
	
	public ResumoEstadoModel toModel(Estado estado) {
		ResumoEstadoModel resumoEstadoModel = createModelWithId(estado.getId(), estado);
		modelMapper.map(estado, resumoEstadoModel);
		
		return resumoEstadoModel;
	}
	
	@Override
	public CollectionModel<ResumoEstadoModel> toCollectionModel(Iterable<? extends Estado> entities) {
		CollectionModel<ResumoEstadoModel> collectionResumoEstadoModel = super.toCollectionModel(entities);
		
		collectionResumoEstadoModel.add(apiLinkBuilder.linkToEstados(IanaLinkRelations.SELF.toString()));
		
		return collectionResumoEstadoModel;
	}
}