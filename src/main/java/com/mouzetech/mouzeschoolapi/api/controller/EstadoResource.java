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

import com.mouzetech.mouzeschoolapi.api.model.input.EstadoInput;
import com.mouzetech.mouzeschoolapi.api.model.output.EstadoModel;
import com.mouzetech.mouzeschoolapi.api.model.output.ResumoEstadoModel;
import com.mouzetech.mouzeschoolapi.domain.model.Estado;
import com.mouzetech.mouzeschoolapi.domain.service.CadastroEstadoService;
import com.mouzetech.mouzeschoolapi.mapper.EstadoModelMapper;
import com.mouzetech.mouzeschoolapi.openapi.controller.EstadoResourceOpenApi;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/estados")
public class EstadoResource implements EstadoResourceOpenApi {

	private CadastroEstadoService cadastroEstadoService;
	
	private EstadoModelMapper estadoModelMapper;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ResumoEstadoModel>> buscarTodos() {
		return ResponseEntity.ok(
				estadoModelMapper.toCollectionResumoEstadoModel(
						cadastroEstadoService.buscarTodos()));
	}
	
	@GetMapping(path = "/{estadoId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EstadoModel> buscarPorId(@PathVariable Long estadoId) {
		Estado estado = cadastroEstadoService.buscarPorId(estadoId);
		return ResponseEntity.ok(
				estadoModelMapper.toEstadoModel(estado));
	}
	@DeleteMapping("/{estadoId}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void excluir(@PathVariable Long estadoId) {
		cadastroEstadoService.excluir(estadoId);
	}
	
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EstadoModel> salvar(@RequestBody @Valid EstadoInput estadoInput){
		Estado estado = estadoModelMapper.toEntity(estadoInput);
		
		estado = cadastroEstadoService.salvar(estado);
		
		return ResponseEntity.ok(estadoModelMapper.toEstadoModel(estado));
	}
	
	@PutMapping("/{estadoId}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void atualizar(@RequestBody @Valid EstadoInput estadoInput, @PathVariable Long estadoId) {
		cadastroEstadoService.atualizar(estadoInput, estadoId);
	}
	
}
