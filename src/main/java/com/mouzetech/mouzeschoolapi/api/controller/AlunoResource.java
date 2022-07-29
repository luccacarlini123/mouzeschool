package com.mouzetech.mouzeschoolapi.api.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
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

import com.mouzetech.mouzeschoolapi.api.model.input.CadastrarAlunoInput;
import com.mouzetech.mouzeschoolapi.api.model.input.EnderecoInput;
import com.mouzetech.mouzeschoolapi.api.model.output.AlunoModel;
import com.mouzetech.mouzeschoolapi.api.model.output.ResumoAlunoModel;
import com.mouzetech.mouzeschoolapi.core.jackson.PageableTranslator;
import com.mouzetech.mouzeschoolapi.domain.repository.AlunoRepository;
import com.mouzetech.mouzeschoolapi.domain.service.CadastroAlunoService;
import com.mouzetech.mouzeschoolapi.mapper.AlunoModelMapper;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/alunos")
@AllArgsConstructor
public class AlunoResource {

	private AlunoRepository alunoRepository;
	
	private CadastroAlunoService cadastroAlunoService;
	
	private AlunoModelMapper alunoModelMapper;
	
	@GetMapping
	public Page<ResumoAlunoModel> buscarAlunos(Pageable pageable){
		pageable = traduzirPageable(pageable);
		
		List<ResumoAlunoModel> alunos = alunoModelMapper.toCollectionResumoAlunoModel(
					alunoRepository.findAll(pageable).getContent());
				
		return new PageImpl<ResumoAlunoModel>(alunos, pageable, pageable.getPageSize());
	}

	@GetMapping("/{alunoId}")
	public ResponseEntity<AlunoModel> buscarPorId(@PathVariable Long alunoId){
		return ResponseEntity.ok(
				alunoModelMapper.toAlunoModel(
						cadastroAlunoService.buscarPorId(alunoId)));
	}
	
	@GetMapping("/email/{email}")
	public ResponseEntity<List<AlunoModel>> buscarPorEmail(@PathVariable("email") String email){
		return ResponseEntity.ok(
				alunoModelMapper.toCollectionAlunoModel(
						alunoRepository.findByEmailContaining(email)));
	}
	
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<AlunoModel>> buscarPorNome(@PathVariable("nome") String nome){
		return ResponseEntity.ok(
				alunoModelMapper.toCollectionAlunoModel(
						alunoRepository.findByNomeContaining(nome)));
	}
	
	@PostMapping
	public ResponseEntity<AlunoModel> cadastrar(@RequestBody @Valid CadastrarAlunoInput dto){
		return ResponseEntity.ok(
					alunoModelMapper.toAlunoModel(
							cadastroAlunoService.matricularAluno(
									alunoModelMapper.toEntity(dto))));
	}
	
	@PutMapping("/{alunoId}/endereco")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void cadastrarEndereco(@RequestBody @Valid EnderecoInput enderecoInput, @PathVariable Long alunoId) {
		cadastroAlunoService.cadastrarEndereco(enderecoInput, alunoId);
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PutMapping("/{alunoId}") 
	public void atualizar(@RequestBody @Valid CadastrarAlunoInput dto, @PathVariable Long alunoId){
		cadastroAlunoService.atualizar(dto, alunoId);
	}
	
	@PutMapping("/{alunoId}/ativar-matricula")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void ativarMatricula(@PathVariable Long alunoId) {
		cadastroAlunoService.ativarMatricula(alunoId);
	}
	
	@PutMapping("/{alunoId}/desativar-matricula")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void desativarMatricula(@PathVariable Long alunoId) {
		cadastroAlunoService.desativarMatricula(alunoId);
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{alunoId}")
	public void excluir(@PathVariable Long alunoId){
		cadastroAlunoService.excluir(alunoId);
	}
	
	private Pageable traduzirPageable(Pageable pageable) {
		var mapeamento = Map.of(
				"nome", "nome");
		
		return PageableTranslator.translate(pageable, mapeamento);
	}
	
}
