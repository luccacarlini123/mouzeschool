package com.mouzetech.mouzeschoolapi.api.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mouzetech.mouzeschoolapi.api.ApiLinkBuilder;
import com.mouzetech.mouzeschoolapi.api.model.output.RootEntryPointModel;
import com.mouzetech.mouzeschoolapi.domain.service.TurmaReportService;
import com.mouzetech.mouzeschoolapi.openapi.controller.RelatoriosResourceOpenApi;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/relatorios")
@AllArgsConstructor
public class RelatoriosController implements RelatoriosResourceOpenApi {

	private TurmaReportService turmaReportService;
	private ApiLinkBuilder apiLinkBuilder;
	
	@GetMapping
	public RootEntryPointModel linksRelatorios() {
		var rootEntryPoint = new RootEntryPointModel();
		
		rootEntryPoint.add(apiLinkBuilder.linkToRelatorioAlunosDaTurma("relatorio-alunos-da-turma", null));
		
		return rootEntryPoint;
	}
	
	@GetMapping(value = "/turmas/{turmaId}/alunos", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<byte[]> relatorioAlunosDaTurma(@PathVariable Long turmaId) {
		byte[] bytesPdf = turmaReportService.emitirRelatorioAlunosDaTurma(turmaId);
		
		var headers = new HttpHeaders();
		headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=alunos-da-turma.pdf");
		
		return ResponseEntity
				.ok()
				.headers(headers)
				.contentType(MediaType.APPLICATION_PDF)
				.body(bytesPdf);
				
	}
	
}