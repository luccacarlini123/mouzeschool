package com.mouzetech.mouzeschoolapi.mapper.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.mouzetech.mouzeschoolapi.api.ApiLinkBuilder;
import com.mouzetech.mouzeschoolapi.api.controller.ProfessorController;
import com.mouzetech.mouzeschoolapi.api.model.output.ResumoProfessorModel;
import com.mouzetech.mouzeschoolapi.domain.model.Professor;

@Component
public class ResumoProfessorModelAssembler extends RepresentationModelAssemblerSupport<Professor, ResumoProfessorModel> {

	public ResumoProfessorModelAssembler() {
		super(ProfessorController.class, ResumoProfessorModel.class);
	}

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private ApiLinkBuilder apiLinkBuilder;
	
	public ResumoProfessorModel toModel(Professor professor) {
		ResumoProfessorModel resumoProfessorModel = createModelWithId(professor.getId(), professor);
		modelMapper.map(professor, resumoProfessorModel);
		
		return resumoProfessorModel;
	}
	
	@Override
	public CollectionModel<ResumoProfessorModel> toCollectionModel(Iterable<? extends Professor> entities) {
		CollectionModel<ResumoProfessorModel> resumoProfessorCollectionModel = super.toCollectionModel(entities);
		
		resumoProfessorCollectionModel.add(apiLinkBuilder.linkToProfessores(IanaLinkRelations.SELF.toString()));
		
		return resumoProfessorCollectionModel;
	}
}