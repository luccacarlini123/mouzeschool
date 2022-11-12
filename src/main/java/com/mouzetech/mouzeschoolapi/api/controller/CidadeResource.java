package com.mouzetech.mouzeschoolapi.api.controller;

import java.util.List;

import javax.validation.Valid;

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

import com.mouzetech.mouzeschoolapi.api.model.input.CadastroCidadeInput;
import com.mouzetech.mouzeschoolapi.api.model.output.CidadeModel;
import com.mouzetech.mouzeschoolapi.api.model.output.CidadeResumoModel;
import com.mouzetech.mouzeschoolapi.domain.model.Cidade;
import com.mouzetech.mouzeschoolapi.domain.service.CadastroCidadeService;
import com.mouzetech.mouzeschoolapi.mapper.CidadeModelMapper;
import com.mouzetech.mouzeschoolapi.openapi.controller.CidadeResourceResourceOpenApi;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RequestMapping("/cidades")
@RestController
public class CidadeResource implements CidadeResourceResourceOpenApi {

	private CadastroCidadeService cadastroCidadeService;
	private CidadeModelMapper cidadeModelMapper;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<CidadeResumoModel> buscarTodos(){
		return cidadeModelMapper
				.toCollectionCidadeResumoModel(cadastroCidadeService.buscarTodos());
	}
	
	@GetMapping(path = "/{cidadeId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CidadeModel> buscarPorId(@PathVariable Long cidadeId){
		return ResponseEntity.ok(
				cidadeModelMapper.toCidadeModel(
						cadastroCidadeService.buscarPorId(cidadeId)));
	}
	
	@DeleteMapping("/{cidadeId}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void excluirPorId(@PathVariable Long cidadeId){
		cadastroCidadeService.excluir(cidadeId);
	}
	
	@PutMapping("/{cidadeId}")
	public void atualizar(@RequestBody @Valid CadastroCidadeInput input, @PathVariable Long cidadeId){
		cadastroCidadeService.atualizar(cidadeId, input);
	}
	
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CidadeModel> salvar(@RequestBody @Valid CadastroCidadeInput input){
		Cidade cidade = cidadeModelMapper.fromCadastroCidadeInput(input);
		
		return ResponseEntity.ok(
				cidadeModelMapper.toCidadeModel(
						cadastroCidadeService.salvar(cidade)));
	}
	
}
