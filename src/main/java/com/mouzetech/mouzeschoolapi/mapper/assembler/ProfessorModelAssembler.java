package com.mouzetech.mouzeschoolapi.mapper.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.mouzetech.mouzeschoolapi.api.ApiLinkBuilder;
import com.mouzetech.mouzeschoolapi.api.controller.ProfessorController;
import com.mouzetech.mouzeschoolapi.api.model.output.ProfessorModel;
import com.mouzetech.mouzeschoolapi.domain.model.Professor;

@Component
public class ProfessorModelAssembler extends RepresentationModelAssemblerSupport<Professor, ProfessorModel> {

	public ProfessorModelAssembler() {
		super(ProfessorController.class, ProfessorModel.class);
	}

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private ApiLinkBuilder apiLinkBuilder;

	@Override
	public ProfessorModel toModel(Professor professor) {
		ProfessorModel professorModel = createModelWithId(professor.getId(), professor);
		modelMapper.map(professor, professorModel);

		if (professor.matriculaAtivada()) {
			professorModel.getMatricula()
					.add(apiLinkBuilder.linkToDesativarMatriculaProfessor("desativar-matricula", professor.getId()));
		}

		if (professor.matriculaDesativada()) {
			professorModel.getMatricula()
					.add(apiLinkBuilder.linkToAtivarMatriculaProfessor("ativar-matricula", professor.getId()));
		}

		professorModel.add(apiLinkBuilder.linkToProfessores("professores"));
		
		return professorModel;
	}

	@Override
	public CollectionModel<ProfessorModel> toCollectionModel(Iterable<? extends Professor> entities) {
		CollectionModel<ProfessorModel> professorCollectionModel = super.toCollectionModel(entities);

		return professorCollectionModel;
	}
}