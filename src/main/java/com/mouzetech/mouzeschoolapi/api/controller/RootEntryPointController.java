package com.mouzetech.mouzeschoolapi.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mouzetech.mouzeschoolapi.api.ApiLinkBuilder;
import com.mouzetech.mouzeschoolapi.openapi.controller.RootEntryPointOpenApi;

@RestController
@RequestMapping
public class RootEntryPointController implements RootEntryPointOpenApi {

	@Autowired
	private ApiLinkBuilder apiLinkBuilder;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public RootEntryPointModel rootEntryPoint() {
		var rootEntryPointModel = new RootEntryPointModel();

		rootEntryPointModel.add(apiLinkBuilder.linkToAlunos("alunos"));
		rootEntryPointModel.add(apiLinkBuilder.linkToMaterias("materias"));
		rootEntryPointModel.add(apiLinkBuilder.linkToProfessores("professores"));
		rootEntryPointModel.add(apiLinkBuilder.linkToTurmas("turmas"));
		rootEntryPointModel.add(apiLinkBuilder.linkToEstados("estados"));
		rootEntryPointModel.add(apiLinkBuilder.linkToCidades("cidades"));
		rootEntryPointModel.add(apiLinkBuilder.linkToNotasDaTurma("notas-por-turma"));

		return rootEntryPointModel;
	}

	public static final class RootEntryPointModel extends RepresentationModel<RootEntryPointModel> {}
}