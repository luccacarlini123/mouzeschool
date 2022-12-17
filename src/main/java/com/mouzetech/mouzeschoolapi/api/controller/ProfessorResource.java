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

import com.mouzetech.mouzeschoolapi.api.model.input.CadastrarProfessorInput;
import com.mouzetech.mouzeschoolapi.api.model.input.EnderecoInput;
import com.mouzetech.mouzeschoolapi.api.model.output.ProfessorModel;
import com.mouzetech.mouzeschoolapi.api.model.output.ResumoProfessorModel;
import com.mouzetech.mouzeschoolapi.domain.repository.ProfessorRepository;
import com.mouzetech.mouzeschoolapi.domain.service.CadastroProfessorService;
import com.mouzetech.mouzeschoolapi.mapper.assembler.EnderecoModelAssembler;
import com.mouzetech.mouzeschoolapi.mapper.assembler.ProfessorModelAssembler;
import com.mouzetech.mouzeschoolapi.mapper.assembler.ResumoProfessorModelAssembler;
import com.mouzetech.mouzeschoolapi.mapper.disassembler.ProfessorModelDisassembler;
import com.mouzetech.mouzeschoolapi.openapi.controller.ProfessorResourceOpenApi;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/professores")
@AllArgsConstructor
public class ProfessorResource implements ProfessorResourceOpenApi {

	private ProfessorRepository professorRepository;
	private ProfessorModelAssembler professorModelMapper;
	private CadastroProfessorService cadastroProfessorService;
	private EnderecoModelAssembler enderecoModelMapper;
	private ResumoProfessorModelAssembler resumoProfessorModelMapper;
	private ProfessorModelDisassembler professorModelDisassembler;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ResumoProfessorModel> buscarProfessores(){
		return resumoProfessorModelMapper.toCollectionModel(professorRepository.buscarProfessoresComDadosResumidos());
	}
	
	@GetMapping(value = "/{professorId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProfessorModel> buscarPorId(@PathVariable Long professorId) {
		return ResponseEntity.ok(professorModelMapper.toModel(cadastroProfessorService.buscarPorId(professorId)));
	}
	
	@GetMapping(value = "/email/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ProfessorModel>> buscarPorEmail(@PathVariable("email") String email){
		return ResponseEntity.ok(
				professorModelMapper.toCollectionModel(
						professorRepository.findByEmailContaining(email)));
	}
	
	@GetMapping(value = "/nome/{nome}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ProfessorModel>> buscarPorNome(@PathVariable("nome") String nome){
		return ResponseEntity.ok(
				professorModelMapper.toCollectionModel(
						professorRepository.findByNomeContaining(nome)));
	}
	
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProfessorModel> cadastrar(@RequestBody @Valid CadastrarProfessorInput dto) {
		return ResponseEntity.ok(
				professorModelMapper.toModel(
						cadastroProfessorService.matricularProfessor(
								professorModelDisassembler.toEntity(dto))));
	}
	
	@PutMapping("/{professorId}/endereco")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void cadastrarEndereco(@RequestBody @Valid EnderecoInput enderecoInput, @PathVariable Long professorId) {
		cadastroProfessorService.cadastrarEndereco(enderecoInput, professorId);
	}
	
	@GetMapping(value = "/{professorId}/endereco", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<?> buscarEndereco(@PathVariable Long professorId) {
		return cadastroProfessorService.buscarEndereco(professorId).isPresent() 
				? ResponseEntity.ok(enderecoModelMapper.toModel(cadastroProfessorService.buscarEndereco(professorId).get()))
				: ResponseEntity.noContent().build();
	}
	
	@PutMapping("/{professorId}") 
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void atualizar(@RequestBody @Valid CadastrarProfessorInput dto, @PathVariable Long professorId){
		cadastroProfessorService.atualizar(dto, professorId);
	}
	
	@DeleteMapping("/{professorId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluir(@PathVariable Long professorId){
		cadastroProfessorService.excluir(professorId);
	}
	
	@PutMapping("/{professorId}/ativar-matricula")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void ativarMatricula(@PathVariable Long professorId) {
		cadastroProfessorService.ativarMatricula(professorId);
	}
	
	@PutMapping("/{professorId}/desativar-matricula")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void desativarMatricula(@PathVariable Long professorId) {
		cadastroProfessorService.desativarMatricula(professorId);
	}
}