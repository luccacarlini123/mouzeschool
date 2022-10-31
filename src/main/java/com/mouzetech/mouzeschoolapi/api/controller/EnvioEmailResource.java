package com.mouzetech.mouzeschoolapi.api.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mouzetech.mouzeschoolapi.api.model.input.EnvioEmailParaTodosInput;
import com.mouzetech.mouzeschoolapi.domain.model.Aluno;
import com.mouzetech.mouzeschoolapi.domain.model.Professor;
import com.mouzetech.mouzeschoolapi.domain.repository.AlunoRepository;
import com.mouzetech.mouzeschoolapi.domain.repository.ProfessorRepository;
import com.mouzetech.mouzeschoolapi.domain.service.CadastroAlunoService;
import com.mouzetech.mouzeschoolapi.domain.service.CadastroProfessorService;
import com.mouzetech.mouzeschoolapi.domain.service.EnvioEmailService;
import com.mouzetech.mouzeschoolapi.domain.service.EnvioEmailService.Mensagem;
import com.mouzetech.mouzeschoolapi.openapi.controller.EnvioEmailResourceOpenApi;

@RestController
@RequestMapping("/envio-email")
public class EnvioEmailResource implements EnvioEmailResourceOpenApi {

	private static final String CAMINHO_TEMPLATE_ENVIO_EMAIL = "envio-email-para-todos-ou-individual.html";
	
	@Autowired
	private AlunoRepository alunoRepository;
	
	@Autowired
	private ProfessorRepository professorRepository;
	
	@Autowired
	private CadastroAlunoService cadastroAlunoService;
	
	@Autowired
	private CadastroProfessorService cadastroProfessorService;
	
	@Autowired
	private EnvioEmailService envioEmailService;
	
	@PostMapping("/alunos")
	public void enviarEmailParaTodosAlunosComMatriculaAtiva(@RequestBody @Valid EnvioEmailParaTodosInput emailInput) {
		List<Aluno> alunosComMatriculaAtiva = alunoRepository.buscarTodosAlunosComMatriculaAtiva();
		List<String> emails = alunosComMatriculaAtiva.stream()
				.map(aluno -> aluno.getEmail()).collect(Collectors.toList());
		
		Mensagem mensagem = Mensagem.builder()
				.assunto(emailInput.getAssunto())
				.variavel("textoCorpo", emailInput.getTextoCorpo())
				.destinatarios(emails)
				.corpo(CAMINHO_TEMPLATE_ENVIO_EMAIL)
				.build();
		
		envioEmailService.enviar(mensagem);
	}
	
	@PostMapping("/alunos/{alunoId}")
	public void enviarEmailParaAluno(@RequestBody @Valid EnvioEmailParaTodosInput emailInput, @PathVariable Long alunoId) {
		Aluno aluno = cadastroAlunoService.buscarPorId(alunoId);
		
		Mensagem mensagem = Mensagem.builder()
				.assunto(emailInput.getAssunto())
				.variavel("textoCorpo", emailInput.getTextoCorpo())
				.destinatarios(Arrays.asList(aluno.getEmail()))
				.corpo(CAMINHO_TEMPLATE_ENVIO_EMAIL)
				.build();
		
		envioEmailService.enviar(mensagem);
	}
	
	@PostMapping("/professores")
	public void enviarEmailParaTodosProfessoresComMatriculaAtiva(@RequestBody @Valid EnvioEmailParaTodosInput emailInput) {
		List<Professor> professoresComMatriculaAtiva = professorRepository.buscarTodosProfessoresComMatriculaAtiva();
		List<String> emails = professoresComMatriculaAtiva.stream()
				.map(professor -> professor.getEmail()).collect(Collectors.toList());
		
		Mensagem mensagem = Mensagem.builder()
				.assunto(emailInput.getAssunto())
				.variavel("textoCorpo", emailInput.getTextoCorpo())
				.destinatarios(emails)
				.corpo(CAMINHO_TEMPLATE_ENVIO_EMAIL)
				.build();
		
		envioEmailService.enviar(mensagem);
	}
	
	@PostMapping("/professores/{professorId}")
	public void enviarEmailParaProfessor(@RequestBody @Valid EnvioEmailParaTodosInput emailInput, @PathVariable Long professorId) {
		Professor professor = cadastroProfessorService.buscarPorId(professorId);
		
		Mensagem mensagem = Mensagem.builder()
				.assunto(emailInput.getAssunto())
				.variavel("textoCorpo", emailInput.getTextoCorpo())
				.destinatarios(Arrays.asList(professor.getEmail()))
				.corpo(CAMINHO_TEMPLATE_ENVIO_EMAIL)
				.build();
		
		envioEmailService.enviar(mensagem);
	}
	
}
