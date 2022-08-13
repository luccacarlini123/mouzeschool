package com.mouzetech.mouzeschoolapi.api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mouzetech.mouzeschoolapi.api.model.output.MediaAlunoModel;
import com.mouzetech.mouzeschoolapi.domain.service.MediaAlunoService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/media/aluno/{alunoId}/turma/{turmaId}")
@AllArgsConstructor
public class MediaAlunoResource {

	private MediaAlunoService mediaAlunoService;
	
	@GetMapping
	public ResponseEntity<?> buscarMediaAluno(@PathVariable Long alunoId, @PathVariable Long turmaId){
		
		List<MediaAlunoModel> medias = mediaAlunoService.buscarMedias(alunoId, turmaId);
		
		return medias.isEmpty() 
				? ResponseEntity.noContent().build() 
				: ResponseEntity.ok(medias);
	}
}