package com.mouzetech.mouzeschoolapi.mapper.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.mouzetech.mouzeschoolapi.api.ApiLinkBuilder;
import com.mouzetech.mouzeschoolapi.api.controller.AlunoController;
import com.mouzetech.mouzeschoolapi.api.model.output.AlunoModel;
import com.mouzetech.mouzeschoolapi.domain.model.Aluno;

@Component
public class AlunoModelAssembler extends RepresentationModelAssemblerSupport<Aluno, AlunoModel> {

	public AlunoModelAssembler() {
		super(AlunoController.class, AlunoModel.class);
	}

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private ApiLinkBuilder apiLinkBuilder;
	
	public AlunoModel toModel(Aluno aluno) {
		AlunoModel alunoModel = createModelWithId(aluno.getId(), aluno); 
		modelMapper.map(aluno, alunoModel);
		
		alunoModel.add(apiLinkBuilder.linkToAlunos("alunos"));
		alunoModel.add(apiLinkBuilder.linkToEnderecoAluno(alunoModel.getId(), "endereco"));
		
		if(aluno.matriculaAtivada()) {
			alunoModel.getMatricula().add(apiLinkBuilder.linkToDesativarMatriculaAluno(alunoModel.getId(), "desativar-matricula"));
		}
		
		if(aluno.matriculaDesativada()) {
			alunoModel.getMatricula().add(apiLinkBuilder.linkToAtivarMatriculaAluno(alunoModel.getId(), "ativar-matricula"));
		}
		
		return alunoModel;
	}
	
	@Override
	public CollectionModel<AlunoModel> toCollectionModel(Iterable<? extends Aluno> entities) {
		CollectionModel<AlunoModel> collectionModelAluno = super.toCollectionModel(entities);
		
		return collectionModelAluno;
	}
}