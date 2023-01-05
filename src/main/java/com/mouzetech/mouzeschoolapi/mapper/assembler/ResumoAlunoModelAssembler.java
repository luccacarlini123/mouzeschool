package com.mouzetech.mouzeschoolapi.mapper.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.mouzetech.mouzeschoolapi.api.ApiLinkBuilder;
import com.mouzetech.mouzeschoolapi.api.controller.AlunoController;
import com.mouzetech.mouzeschoolapi.api.model.output.ResumoAlunoModel;
import com.mouzetech.mouzeschoolapi.domain.model.Aluno;

@Component
public class ResumoAlunoModelAssembler extends RepresentationModelAssemblerSupport<Aluno, ResumoAlunoModel> {

	public ResumoAlunoModelAssembler() {
		super(AlunoController.class, ResumoAlunoModel.class);
	}

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private ApiLinkBuilder apiLinkBuilder;
	
	public ResumoAlunoModel toModel(Aluno aluno) {
		ResumoAlunoModel resumoAlunoModel = createModelWithId(aluno.getId(), aluno);
		modelMapper.map(aluno, resumoAlunoModel);
		
		resumoAlunoModel.add(apiLinkBuilder.linkToAlunos("alunos"));
		resumoAlunoModel.add(apiLinkBuilder.linkToEnderecoAluno(resumoAlunoModel.getId(), "endereco"));
		
		if(aluno.matriculaAtivada()) {
			resumoAlunoModel.add(apiLinkBuilder.linkToDesativarMatriculaAluno(resumoAlunoModel.getId(), "desativar-matricula"));
		}
		
		if(aluno.matriculaDesativada()) {
			resumoAlunoModel.add(apiLinkBuilder.linkToAtivarMatriculaAluno(resumoAlunoModel.getId(), "ativar-matricula"));
		}
		
		return resumoAlunoModel;
	}
	
	public List<ResumoAlunoModel> toCollectionModel(List<Aluno> alunos){
		return alunos.stream()
				.map(aluno -> toModel(aluno))
				.collect(Collectors.toList());
	}
}