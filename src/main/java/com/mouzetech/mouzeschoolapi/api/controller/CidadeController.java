package com.mouzetech.mouzeschoolapi.api.controller;

import javax.validation.Valid;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mouzetech.mouzeschoolapi.api.model.input.CidadeInput;
import com.mouzetech.mouzeschoolapi.api.model.output.CidadeModel;
import com.mouzetech.mouzeschoolapi.api.model.output.CidadeResumoModel;
import com.mouzetech.mouzeschoolapi.domain.model.Cidade;
import com.mouzetech.mouzeschoolapi.domain.service.CadastroCidadeService;
import com.mouzetech.mouzeschoolapi.mapper.assembler.CidadeModelAssembler;
import com.mouzetech.mouzeschoolapi.mapper.assembler.CidadeResumoModelAssembler;
import com.mouzetech.mouzeschoolapi.mapper.disassembler.CidadeModelDisassembler;
import com.mouzetech.mouzeschoolapi.openapi.controller.CidadeResourceResourceOpenApi;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RequestMapping("/cidades")
@RestController
public class CidadeController implements CidadeResourceResourceOpenApi {

	private CadastroCidadeService cadastroCidadeService;
	private CidadeModelAssembler cidadeModelAssembler;
	private CidadeResumoModelAssembler cidadeResumoModelAssembler;
	private CidadeModelDisassembler cidadeModelDisassembler;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public CollectionModel<CidadeResumoModel> buscarTodos(){
		return cidadeResumoModelAssembler
				.toCollectionModel(cadastroCidadeService.buscarTodos());
	}
	
	@GetMapping(path = "/{cidadeId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CidadeModel> buscarPorId(@PathVariable Long cidadeId){
		return ResponseEntity.ok(
				cidadeModelAssembler.toModel(
						cadastroCidadeService.buscarPorId(cidadeId)));
	}
	
	@DeleteMapping("/{cidadeId}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void excluirPorId(@PathVariable Long cidadeId){
		cadastroCidadeService.excluir(cidadeId);
	}
	
	@PutMapping("/{cidadeId}")
	public void atualizar(@RequestBody @Valid CidadeInput input, @PathVariable Long cidadeId){
		cadastroCidadeService.atualizar(cidadeId, input);
	}
	
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CidadeModel> salvar(@RequestBody @Valid CidadeInput input){
		Cidade cidade = cidadeModelDisassembler.toEntity(input);
		
		return ResponseEntity.ok(
				cidadeModelAssembler.toModel(
						cadastroCidadeService.salvar(cidade)));
	}
	
}
