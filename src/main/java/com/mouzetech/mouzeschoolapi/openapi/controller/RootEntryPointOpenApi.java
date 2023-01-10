package com.mouzetech.mouzeschoolapi.openapi.controller;

import com.mouzetech.mouzeschoolapi.api.model.output.RootEntryPointModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Home")
public interface RootEntryPointOpenApi {

	@ApiOperation(value = "Apresenta alguns endpoints para desconhecedores da API a usarem sem conhecimento.")
	RootEntryPointModel rootEntryPoint();
}