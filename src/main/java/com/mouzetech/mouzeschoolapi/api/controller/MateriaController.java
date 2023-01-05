package com.mouzetech.mouzeschoolapi.api.controller;

import java.util.List;

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

import com.mouzetech.mouzeschoolapi.api.model.input.MateriaInput;
import com.mouzetech.mouzeschoolapi.api.model.output.MateriaModel;
import com.mouzetech.mouzeschoolapi.api.model.output.ResumoMateriaModel;
import com.mouzetech.mouzeschoolapi.domain.model.Materia;
import com.mouzetech.mouzeschoolapi.domain.repository.MateriaRepository;
import com.mouzetech.mouzeschoolapi.domain.service.CadastroMateriaService;
import com.mouzetech.mouzeschoolapi.mapper.assembler.MateriaModelAssembler;
import com.mouzetech.mouzeschoolapi.mapper.assembler.ResumoMateriaModelAssembler;
import com.mouzetech.mouzeschoolapi.openapi.controller.MateriaResourceOpenApi;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/materias")
@AllArgsConstructor
public class MateriaController implements MateriaResourceOpenApi {

	private MateriaRepository materiaRepository;
	private CadastroMateriaService cadastroMateriaService;
	private MateriaModelAssembler materiaModelMapper;
	private ResumoMateriaModelAssembler resumoMateriaModelAssembler;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.OK)
	public CollectionModel<ResumoMateriaModel> buscarTodas() {
		return resumoMateriaModelAssembler.toCollectionModel(materiaRepository.findAll());
	}
	
	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping(path = "/{materiaId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public MateriaModel buscarPorId(@PathVariable Long materiaId) {
		return materiaModelMapper.toModel(cadastroMateriaService.buscarPorId(materiaId));
	}
	
	@GetMapping(value = "/nome/{nome}", produces = MediaType.APPLICATION_JSON_VALUE)
	public CollectionModel<MateriaModel> buscarPorNomeContaining(@PathVariable String nome) {
		List<Materia> materias = cadastroMateriaService.buscarPorNomeContaining(nome);
		return materiaModelMapper.toCollectionModel(materias);
	}
	
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.OK)
	public MateriaModel cadastrar(@RequestBody @Valid MateriaInput dto) {
		return materiaModelMapper.toModel(cadastroMateriaService.cadastrar(dto));
	}
	
	@PutMapping("/ativar/{materiaId}")
	public ResponseEntity<Void> ativarMateria(@PathVariable Long materiaId){
		cadastroMateriaService.ativar(materiaId);
		return ResponseEntity.ok().build();
	}
	
	@PutMapping("/desativar/{materiaId}")
	public ResponseEntity<Void> desativarMateria(@PathVariable Long materiaId){
		cadastroMateriaService.desativar(materiaId);
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("/{materiaId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluir(@PathVariable Long materiaId) {
		cadastroMateriaService.excluir(materiaId);
	}
	
}
