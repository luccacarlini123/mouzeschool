package com.mouzetech.mouzeschoolapi.mapper.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
			alunoModel.add(apiLinkBuilder.linkToDesativarMatriculaAluno(alunoModel.getId(), "desativar-matricula"));
		}
		
		if(aluno.matriculaDesativada()) {
			alunoModel.add(apiLinkBuilder.linkToAtivarMatriculaAluno(alunoModel.getId(), "ativar-matricula"));
		}
		
		return alunoModel;
	}
	
	public List<AlunoModel> toCollectionModel(List<Aluno> alunos){
		return alunos.stream()
				.map(aluno -> toModel(aluno))
				.collect(Collectors.toList());
	}
}