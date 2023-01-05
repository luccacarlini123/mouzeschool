package com.mouzetech.mouzeschoolapi.mapper.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.mouzetech.mouzeschoolapi.api.ApiLinkBuilder;
import com.mouzetech.mouzeschoolapi.api.controller.TurmaController;
import com.mouzetech.mouzeschoolapi.api.model.output.TurmaModel;
import com.mouzetech.mouzeschoolapi.domain.model.Turma;

@Component
public class TurmaModelAssembler extends RepresentationModelAssemblerSupport<Turma, TurmaModel> {

	public TurmaModelAssembler() {
		super(TurmaController.class, TurmaModel.class);
	}

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private ApiLinkBuilder apiLinkBuilder;
	
	public TurmaModel toModel(Turma turma) {
		TurmaModel turmaModel = createModelWithId(turma.getId(), turma);
		modelMapper.map(turma, turmaModel);
		
		if(turma.ativada()) {
			turmaModel.add(apiLinkBuilder.linkToDesativarTurma("desativar-turma", turma.getId()));
		}
		
		if(turma.desativada()) {
			turmaModel.add(apiLinkBuilder.linkToAtivarTurma("ativar-turma", turma.getId()));
		}
		
		turmaModel.add(apiLinkBuilder.linkToGradeCurricularTurma("grade-curricular", turma.getId()));
		
		return turmaModel;
	}
}