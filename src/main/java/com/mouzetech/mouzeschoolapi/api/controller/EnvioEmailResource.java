package com.mouzetech.mouzeschoolapi.api.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mouzetech.mouzeschoolapi.api.model.input.EnvioEmailParaTodosInput;
import com.mouzetech.mouzeschoolapi.domain.model.Aluno;
import com.mouzetech.mouzeschoolapi.domain.repository.AlunoRepository;
import com.mouzetech.mouzeschoolapi.domain.service.EnvioEmailService;
import com.mouzetech.mouzeschoolapi.domain.service.EnvioEmailService.Mensagem;
import com.mouzetech.mouzeschoolapi.openapi.controller.EnvioEmailResourceOpenApi;

@RestController
@RequestMapping("/envio-email")
public class EnvioEmailResource implements EnvioEmailResourceOpenApi {

	@Autowired
	private AlunoRepository alunoRepository;
	
	@Autowired
	private EnvioEmailService envioEmailService;
	
	@PutMapping("/alunos")
	public void enviarEmailParaTodosAlunosComMatriculaAtiva(@RequestBody @Valid EnvioEmailParaTodosInput emailInput) {
		List<Aluno> alunosComMatriculaAtiva = alunoRepository.buscarTodosAlunosComMatriculaAtiva();
		List<String> emails = alunosComMatriculaAtiva.stream()
				.map(aluno -> aluno.getEmail()).collect(Collectors.toList());
		
		Mensagem mensagem = Mensagem.builder()
				.assunto(emailInput.getAssunto())
				.variavel("textoCorpo", emailInput.getTextoCorpo())
				.destinatarios(emails)
				.corpo("envio-email-para-todos-alunos.html")
				.build();
		
		envioEmailService.enviar(mensagem);
	}
	
}
