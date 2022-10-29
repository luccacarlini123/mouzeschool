package com.mouzetech.mouzeschoolapi.api.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mouzetech.mouzeschoolapi.api.model.input.FotoPessoaInput;
import com.mouzetech.mouzeschoolapi.api.model.output.FotoPessoaModel;
import com.mouzetech.mouzeschoolapi.domain.exception.EntidadeNaoEncontradaException;
import com.mouzetech.mouzeschoolapi.domain.model.Aluno;
import com.mouzetech.mouzeschoolapi.domain.model.FotoAluno;
import com.mouzetech.mouzeschoolapi.domain.service.CadastroAlunoService;
import com.mouzetech.mouzeschoolapi.domain.service.CatalogoFotoPessoaService;
import com.mouzetech.mouzeschoolapi.domain.service.FotoStorageService;
import com.mouzetech.mouzeschoolapi.domain.service.FotoStorageService.FotoRecuperada;
import com.mouzetech.mouzeschoolapi.mapper.FotoPessoaModelAssembler;
import com.mouzetech.mouzeschoolapi.openapi.controller.CatalogoFotoAlunoControllerOpenApi;

@RestController
@RequestMapping("/alunos/{alunoId}/foto")
public class CatalogoFotoAlunoController implements CatalogoFotoAlunoControllerOpenApi{

	@Autowired
	private CatalogoFotoPessoaService catalogoFotoPessoaService;
	
	@Autowired
	private FotoPessoaModelAssembler fotoPessoaModelAssembler;
	
	@Autowired
	private CadastroAlunoService cadastroAlunoService;
	
	@Autowired
	private FotoStorageService fotoStorageService;
	
	@PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public FotoPessoaModel atualizarFoto(@PathVariable Long alunoId, @Valid FotoPessoaInput fotoPessoaInput,
			@RequestPart(required = true) MultipartFile arquivo) throws IOException {
		Aluno aluno = cadastroAlunoService.buscarPorId(alunoId);
		
//		MultipartFile arquivo = fotoPessoaInput.getArquivo();
		
		FotoAluno fotoAluno = new FotoAluno();
		fotoAluno.setAluno(aluno);
		fotoAluno.setDescricao(fotoPessoaInput.getDescricao());
		fotoAluno.setNomeArquivo(arquivo.getOriginalFilename());
		fotoAluno.setContentType(arquivo.getContentType());
		fotoAluno.setTamanho(arquivo.getSize());
		
		return fotoPessoaModelAssembler.toModel(catalogoFotoPessoaService.salvarFotoAluno(fotoAluno, arquivo.getInputStream()));
	}
	
	@GetMapping(produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
	public ResponseEntity<?> buscarFotoDoAluno(@PathVariable Long alunoId,
			@RequestHeader("accept") String acceptHeaders) throws HttpMediaTypeNotAcceptableException{
		try {
			FotoAluno fotoAluno = catalogoFotoPessoaService.buscarFotoAluno(alunoId);
			
			MediaType mediaType = MediaType.parseMediaType(fotoAluno.getContentType());
			List<MediaType> mediaTypesAceitas = MediaType.parseMediaTypes(acceptHeaders);
			
			verificarCompatibilidadeMediaType(mediaTypesAceitas, mediaType);
			
			FotoRecuperada fotoRecuperada = fotoStorageService.recuperar(fotoAluno.getNomeArquivo());
			
			if(fotoRecuperada.temUrl()) {
				return ResponseEntity.status(HttpStatus.FOUND)
							.header(HttpHeaders.LOCATION, fotoRecuperada.getUrl())
							.build();
			} else {
				return ResponseEntity.ok()
						.contentType(mediaType)
						.body(new InputStreamResource(fotoRecuperada.getInputStream()));
			}
		} catch(EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluirFotoDoAluno(@PathVariable Long alunoId) {
		catalogoFotoPessoaService.excluirFotoDoAluno(alunoId);
	}

	private void verificarCompatibilidadeMediaType(List<MediaType> mediaTypesAceitas, MediaType mediaType) throws HttpMediaTypeNotAcceptableException {
		boolean compativel = mediaTypesAceitas.stream().anyMatch(mediaTypeAceita -> mediaTypeAceita.isCompatibleWith(mediaType));
		
		if(!compativel) {
			throw new HttpMediaTypeNotAcceptableException(mediaTypesAceitas);
		}
	}
	
}
