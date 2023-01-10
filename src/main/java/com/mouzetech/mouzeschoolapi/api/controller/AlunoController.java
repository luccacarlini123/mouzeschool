package com.mouzetech.mouzeschoolapi.api.controller;

import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.PagedModel;
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

import com.mouzetech.mouzeschoolapi.api.ApiLinkBuilder;
import com.mouzetech.mouzeschoolapi.api.model.input.AlunoInput;
import com.mouzetech.mouzeschoolapi.api.model.input.EnderecoInput;
import com.mouzetech.mouzeschoolapi.api.model.output.AlunoModel;
import com.mouzetech.mouzeschoolapi.api.model.output.EnderecoModel;
import com.mouzetech.mouzeschoolapi.api.model.output.ResumoAlunoModel;
import com.mouzetech.mouzeschoolapi.core.jackson.PageableTranslator;
import com.mouzetech.mouzeschoolapi.domain.model.Aluno;
import com.mouzetech.mouzeschoolapi.domain.model.Endereco;
import com.mouzetech.mouzeschoolapi.domain.repository.AlunoRepository;
import com.mouzetech.mouzeschoolapi.domain.service.CadastroAlunoService;
import com.mouzetech.mouzeschoolapi.mapper.assembler.AlunoModelAssembler;
import com.mouzetech.mouzeschoolapi.mapper.assembler.EnderecoModelAssembler;
import com.mouzetech.mouzeschoolapi.mapper.assembler.ResumoAlunoModelAssembler;
import com.mouzetech.mouzeschoolapi.mapper.disassembler.AlunoModelDisassembler;
import com.mouzetech.mouzeschoolapi.openapi.controller.AlunoResourceOpenApi;

@RestController
@RequestMapping("/alunos")
public class AlunoController implements AlunoResourceOpenApi {

	@Autowired
	private AlunoRepository alunoRepository;

	@Autowired
	private CadastroAlunoService cadastroAlunoService;

	@Autowired
	private AlunoModelAssembler alunoModelMapper;

	@Autowired
	private EnderecoModelAssembler enderecoModelMapper;

	@Autowired
	private ResumoAlunoModelAssembler resumoAlunoModelAssembler;

	@Autowired
	AlunoModelDisassembler alunoModelDisassembler;

	@Autowired
	private PagedResourcesAssembler<Aluno> pagedResourcesAssemblerAluno;

	@Autowired
	private ApiLinkBuilder apiLinkBuilder;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public PagedModel<ResumoAlunoModel> buscarAlunos(@PageableDefault(size = 2) Pageable pageable) {
		pageable = traduzirPageable(pageable);

		Page<Aluno> resumoAlunoModelPage = alunoRepository.findAll(pageable);

		PagedModel<ResumoAlunoModel> pagedAlunoModel = pagedResourcesAssemblerAluno.toModel(resumoAlunoModelPage,
				resumoAlunoModelAssembler);

		return pagedAlunoModel;
	}

	@GetMapping(path = "/{alunoId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AlunoModel> buscarPorId(@PathVariable Long alunoId) {
		return ResponseEntity.ok(alunoModelMapper.toModel(cadastroAlunoService.buscarPorId(alunoId)));
	}

	@GetMapping(value = "/email/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
	public CollectionModel<AlunoModel> buscarPorEmail(@PathVariable("email") String email) {
		return alunoModelMapper.toCollectionModel(alunoRepository.findByEmailContaining(email));
	}

	@GetMapping(value = "/nome/{nome}", produces = MediaType.APPLICATION_JSON_VALUE)
	public CollectionModel<AlunoModel> buscarPorNome(@PathVariable("nome") String nome) {
		return alunoModelMapper.toCollectionModel(alunoRepository.findByNomeContaining(nome));
	}

	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AlunoModel> cadastrar(@RequestBody @Valid AlunoInput dto) {
		return ResponseEntity.ok(
				alunoModelMapper.toModel(cadastroAlunoService.matricularAluno(alunoModelDisassembler.toEntity(dto))));
	}

	@PutMapping("/{alunoId}/endereco")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void cadastrarEndereco(@RequestBody @Valid EnderecoInput enderecoInput, @PathVariable Long alunoId) {
		cadastroAlunoService.cadastrarEndereco(enderecoInput, alunoId);
	}

	@GetMapping(value = "/{alunoId}/endereco", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<?> buscarEndereco(@PathVariable Long alunoId) {
		Optional<Endereco> optionalEndereco = cadastroAlunoService.buscarEndereco(alunoId);
		
		if(optionalEndereco.isPresent()) {
			EnderecoModel enderecoModel = enderecoModelMapper.toModel(optionalEndereco.get());
	
			enderecoModel.add(apiLinkBuilder.linkToEnderecoAluno(alunoId, IanaLinkRelations.SELF.toString()));
	
			enderecoModel.getCidade()
					.add(apiLinkBuilder.linkToCidade(enderecoModel.getCidade().getId(), IanaLinkRelations.SELF.toString()));
	
			enderecoModel.getCidade().getEstado().add(apiLinkBuilder
					.linkToEstado(enderecoModel.getCidade().getEstado().getId(), IanaLinkRelations.SELF.toString()));
			
			return ResponseEntity.ok(enderecoModel);
		}

		return ResponseEntity.noContent().build();
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PutMapping("/{alunoId}")
	public void atualizar(@RequestBody @Valid AlunoInput dto, @PathVariable Long alunoId) {
		cadastroAlunoService.atualizar(dto, alunoId);
	}

	@PutMapping("/{alunoId}/ativar-matricula")
	public ResponseEntity<Void> ativarMatricula(@PathVariable Long alunoId) {
		cadastroAlunoService.ativarMatricula(alunoId);

		return ResponseEntity.noContent().build();
	}

	@PutMapping("/{alunoId}/desativar-matricula")
	public ResponseEntity<Void> desativarMatricula(@PathVariable Long alunoId) {
		cadastroAlunoService.desativarMatricula(alunoId);

		return ResponseEntity.noContent().build();
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{alunoId}")
	public void excluir(@PathVariable Long alunoId) {
		cadastroAlunoService.excluir(alunoId);
	}

	private Pageable traduzirPageable(Pageable pageable) {
		var mapeamento = Map.of("nome", "nome");

		return PageableTranslator.translate(pageable, mapeamento);
	}

}
