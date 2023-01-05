package com.mouzetech.mouzeschoolapi.mapper.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.mouzetech.mouzeschoolapi.api.ApiLinkBuilder;
import com.mouzetech.mouzeschoolapi.api.controller.MateriaController;
import com.mouzetech.mouzeschoolapi.api.model.output.MateriaModel;
import com.mouzetech.mouzeschoolapi.domain.model.Materia;

@Component
public class MateriaModelAssembler extends RepresentationModelAssemblerSupport<Materia, MateriaModel> {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private ApiLinkBuilder apiLinkBuilder;
	
	public MateriaModelAssembler() {
		super(MateriaController.class, MateriaModel.class);
	}
	
	public MateriaModel toModel(Materia materia) {
		MateriaModel materiaModel = createModelWithId(materia.getId(), materia);
		modelMapper.map(materia, materiaModel);
		
		if(materia.ativada()) {
			materiaModel.add(apiLinkBuilder.linkToDesativarMateria("desativar", materia.getId()));
		}
		
		if(materia.desativada()) {
			materiaModel.add(apiLinkBuilder.linkToAtivarMateria("ativar", materia.getId()));
		}
		
		return materiaModel;
	}
	
	@Override
	public CollectionModel<MateriaModel> toCollectionModel(Iterable<? extends Materia> entities) {
		CollectionModel<MateriaModel> materiaCollectionModel = super.toCollectionModel(entities);
		
		
		return materiaCollectionModel;
	}
}