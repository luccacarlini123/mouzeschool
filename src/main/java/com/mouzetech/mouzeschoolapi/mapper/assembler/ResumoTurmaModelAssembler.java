package com.mouzetech.mouzeschoolapi.mapper.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.mouzetech.mouzeschoolapi.api.ApiLinkBuilder;
import com.mouzetech.mouzeschoolapi.api.controller.TurmaController;
import com.mouzetech.mouzeschoolapi.api.model.output.ResumoTurmaModel;
import com.mouzetech.mouzeschoolapi.domain.model.Turma;

@Component
public class ResumoTurmaModelAssembler extends RepresentationModelAssemblerSupport<Turma, ResumoTurmaModel> {

	public ResumoTurmaModelAssembler() {
		super(TurmaController.class, ResumoTurmaModel.class);
	}

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private ApiLinkBuilder apiLinkBuilder;
	
	public ResumoTurmaModel toModel(Turma turma) {
		ResumoTurmaModel resumoTurmaModel = createModelWithId(turma.getId(), turma);
		modelMapper.map(turma, resumoTurmaModel);
		
		return resumoTurmaModel;
	}
	
	@Override
	public CollectionModel<ResumoTurmaModel> toCollectionModel(Iterable<? extends Turma> entities) {
		CollectionModel<ResumoTurmaModel> resumoTurmaCollectionModel = super.toCollectionModel(entities);
		
		resumoTurmaCollectionModel.add(apiLinkBuilder.linkToTurmas(IanaLinkRelations.SELF.toString()));
		
		return resumoTurmaCollectionModel;
	}
}