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
import com.mouzetech.mouzeschoolapi.mapper.EnderecoModelMapper;
import com.mouzetech.mouzeschoolapi.mapper.ProfessorModelMapper;
import com.mouzetech.mouzeschoolapi.openapi.controller.ProfessorResourceOpenApi;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/professores")
@AllArgsConstructor
public class ProfessorResource implements ProfessorResourceOpenApi {

	private ProfessorRepository professorRepository;
	private ProfessorModelMapper professorModelMapper;
	private CadastroProfessorService cadastroProfessorService;
	private EnderecoModelMapper enderecoModelMapper;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ResumoProfessorModel> buscarProfessores(){
		return professorModelMapper.toCollectionResumoProfessorDTO(professorRepository.buscarProfessoresComDadosResumidos());
	}
	
	@GetMapping(value = "/{professorId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProfessorModel> buscarPorId(@PathVariable Long professorId) {
		return ResponseEntity.ok(professorModelMapper.toProfessorDTO(cadastroProfessorService.buscarPorId(professorId)));
	}
	
	@GetMapping(value = "/email/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ProfessorModel>> buscarPorEmail(@PathVariable("email") String email){
		return ResponseEntity.ok(
				professorModelMapper.toCollectionProfessorDTO(
						professorRepository.findByEmailContaining(email)));
	}
	
	@GetMapping(value = "/nome/{nome}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ProfessorModel>> buscarPorNome(@PathVariable("nome") String nome){
		return ResponseEntity.ok(
				professorModelMapper.toCollectionProfessorDTO(
						professorRepository.findByNomeContaining(nome)));
	}
	
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProfessorModel> cadastrar(@RequestBody @Valid CadastrarProfessorInput dto) {
		return ResponseEntity.ok(
				professorModelMapper.toProfessorDTO(
						cadastroProfessorService.matricularProfessor(
								professorModelMapper.toEntity(dto))));
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
				? ResponseEntity.ok(enderecoModelMapper.toEnderecoModel(cadastroProfessorService.buscarEndereco(professorId).get()))
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