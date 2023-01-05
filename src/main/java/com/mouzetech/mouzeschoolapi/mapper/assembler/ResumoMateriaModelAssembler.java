package com.mouzetech.mouzeschoolapi.mapper.assembler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.mouzetech.mouzeschoolapi.api.ApiLinkBuilder;
import com.mouzetech.mouzeschoolapi.api.controller.MateriaController;
import com.mouzetech.mouzeschoolapi.api.model.output.ResumoMateriaModel;
import com.mouzetech.mouzeschoolapi.domain.model.Materia;

@Component
public class ResumoMateriaModelAssembler extends RepresentationModelAssemblerSupport<Materia, ResumoMateriaModel> {

	public ResumoMateriaModelAssembler() {
		super(MateriaController.class, ResumoMateriaModel.class);
	}

	@Autowired
	private ApiLinkBuilder apiLinkBuilder;
	
	@Override
	public ResumoMateriaModel toModel(Materia materia) {
		ResumoMateriaModel resumoMateriaModel = createModelWithId(materia.getId(), materia);
		resumoMateriaModel.setId(materia.getId());
		resumoMateriaModel.setGrauEnsino(materia.getGrauEnsino().toString());
		resumoMateriaModel.setSerieEnsino(materia.getSerieEnsino().toString());
		resumoMateriaModel.setStatus(materia.getStatus().toString());
		resumoMateriaModel.setNome(materia.getNome());
		
		if(materia.desativada()) {
			resumoMateriaModel.add(apiLinkBuilder.linkToAtivarMateria("ativar", materia.getId()));
		}
		
		if(materia.ativada()) {
			resumoMateriaModel.add(apiLinkBuilder.linkToDesativarMateria("desativar", materia.getId()));
		}
		
		return resumoMateriaModel;
	}	
	
	@Override
	public CollectionModel<ResumoMateriaModel> toCollectionModel(Iterable<? extends Materia> entities) {
		CollectionModel<ResumoMateriaModel> resumoMateriaCollectionModel = super.toCollectionModel(entities);
		
		resumoMateriaCollectionModel.add(apiLinkBuilder.linkToMaterias(IanaLinkRelations.SELF.toString()));
		
		return resumoMateriaCollectionModel;
	}
}