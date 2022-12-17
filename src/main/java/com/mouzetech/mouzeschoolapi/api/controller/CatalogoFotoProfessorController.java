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
import com.mouzetech.mouzeschoolapi.domain.model.FotoProfessor;
import com.mouzetech.mouzeschoolapi.domain.model.Professor;
import com.mouzetech.mouzeschoolapi.domain.service.CadastroProfessorService;
import com.mouzetech.mouzeschoolapi.domain.service.CatalogoFotoPessoaService;
import com.mouzetech.mouzeschoolapi.domain.service.FotoStorageService;
import com.mouzetech.mouzeschoolapi.domain.service.FotoStorageService.FotoRecuperada;
import com.mouzetech.mouzeschoolapi.mapper.assembler.FotoPessoaModelAssembler;
import com.mouzetech.mouzeschoolapi.openapi.controller.CatalogoFotoProfessorControllerOpenApi;

@RestController
@RequestMapping("/professores/{professorId}/foto")
public class CatalogoFotoProfessorController implements CatalogoFotoProfessorControllerOpenApi {

	@Autowired
	private CatalogoFotoPessoaService catalogoFotoPessoaService;
	
	@Autowired
	private FotoPessoaModelAssembler fotoPessoaModelAssembler;
	
	@Autowired
	private CadastroProfessorService cadastroProfessorService;
	
	@Autowired
	private FotoStorageService fotoStorageService;
	
	@PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public FotoPessoaModel atualizarFoto(@PathVariable Long professorId, @Valid FotoPessoaInput fotoPessoaInput, @RequestPart(required = true) MultipartFile arquivo) throws IOException {
		Professor professor = cadastroProfessorService.buscarPorId(professorId);
		
//		MultipartFile arquivo = fotoPessoaInput.getArquivo();
		
		FotoProfessor fotoProfessor = new FotoProfessor();
		fotoProfessor.setProfessor(professor);
		fotoProfessor.setDescricao(fotoPessoaInput.getDescricao());
		fotoProfessor.setNomeArquivo(arquivo.getOriginalFilename());
		fotoProfessor.setContentType(arquivo.getContentType());
		fotoProfessor.setTamanho(arquivo.getSize());
		
		return fotoPessoaModelAssembler.toModel(catalogoFotoPessoaService.salvarFotoProfessor(fotoProfessor, arquivo.getInputStream()));
	}
	
	@GetMapping(produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
	public ResponseEntity<?> buscarFotoDoProfessor(@PathVariable Long professorId,
			@RequestHeader("accept") String acceptHeaders) throws HttpMediaTypeNotAcceptableException{
		try {
			FotoProfessor fotoProfessor = catalogoFotoPessoaService.buscarFotoProfessor(professorId);
			
			MediaType mediaType = MediaType.parseMediaType(fotoProfessor.getContentType());
			List<MediaType> mediaTypesAceitas = MediaType.parseMediaTypes(acceptHeaders);
			
			verificarCompatibilidadeMediaType(mediaTypesAceitas, mediaType);
			
			FotoRecuperada fotoRecuperada = fotoStorageService.recuperar(fotoProfessor.getNomeArquivo());
			
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
	public void excluirFotoDoProfessor(@PathVariable Long professorId) {
		catalogoFotoPessoaService.excluirFotoDoProfessor(professorId);
	}

	private void verificarCompatibilidadeMediaType(List<MediaType> mediaTypesAceitas, MediaType mediaType) throws HttpMediaTypeNotAcceptableException {
		boolean compativel = mediaTypesAceitas.stream().anyMatch(mediaTypeAceita -> mediaTypeAceita.isCompatibleWith(mediaType));
		
		if(!compativel) {
			throw new HttpMediaTypeNotAcceptableException(mediaTypesAceitas);
		}
	}
	
}
