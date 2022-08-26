package com.mouzetech.mouzeschoolapi.domain.service;

import java.io.InputStream;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mouzetech.mouzeschoolapi.domain.exception.FotoPessoaNaoEncontradaException;
import com.mouzetech.mouzeschoolapi.domain.model.FotoAluno;
import com.mouzetech.mouzeschoolapi.domain.model.FotoProfessor;
import com.mouzetech.mouzeschoolapi.domain.repository.AlunoRepository;
import com.mouzetech.mouzeschoolapi.domain.repository.ProfessorRepository;
import com.mouzetech.mouzeschoolapi.domain.service.FotoStorageService.NovaFoto;

@Service
public class CatalogoFotoPessoaService {
	
	@Autowired
	private AlunoRepository alunoRepository;
	
	@Autowired
	private ProfessorRepository professorRepository;
	
	@Autowired
	private FotoStorageService fotoStorageService;
	
	@Transactional
	public FotoAluno salvarFotoAluno(FotoAluno fotoAluno, InputStream inputStream) {
		Long alunoId = fotoAluno.getAlunoId();
		String nomeArquivoExistente = null;
		
		Optional<FotoAluno> fotoAlunoOptional = alunoRepository.buscarFotoAluno(alunoId);
		
		if(fotoAlunoOptional.isPresent()) {
			nomeArquivoExistente = fotoAlunoOptional.get().getNomeArquivo();
			alunoRepository.excluirFoto(fotoAlunoOptional.get());
		}
		
		String novoNomeArquivo = 
				fotoStorageService.gerarNomeArquivo(fotoAluno.getNomeArquivo());
		
		fotoAluno.setNomeArquivo(novoNomeArquivo);
		fotoAluno = alunoRepository.salvarFoto(fotoAluno);
		alunoRepository.flush();
		
		NovaFoto novaFoto = NovaFoto.builder()
								.nomeArquivo(novoNomeArquivo)
								.inputStream(inputStream)
								.contentType(fotoAluno.getContentType())
								.size(fotoAluno.getTamanho())
								.build();
		
		fotoStorageService.substituir(novaFoto, nomeArquivoExistente);
		
		return fotoAluno;
	}
	
	@Transactional
	public void excluirFotoDoAluno(Long alunoId) {
		FotoAluno fotoAluno = buscarFotoAluno(alunoId);
		
		alunoRepository.excluirFoto(fotoAluno);
		alunoRepository.flush();
		
		fotoStorageService.remover(fotoAluno.getNomeArquivo());
	}
	
	@Transactional
	public FotoProfessor salvarFotoProfessor(FotoProfessor fotoProfessor, InputStream inputStream) {
		Long professorId = fotoProfessor.getId();
		String nomeArquivoExistente = null;
		
		Optional<FotoProfessor> fotoProfessorOptional = professorRepository.buscarFotoDoProfessor(professorId);
		
		if(fotoProfessorOptional.isPresent()) {
			nomeArquivoExistente = fotoProfessorOptional.get().getNomeArquivo();
			professorRepository.excluirFoto(fotoProfessorOptional.get());
		}
		
		String novoNomeArquivo = fotoStorageService.gerarNomeArquivo(nomeArquivoExistente);
		
		fotoProfessor.setNomeArquivo(novoNomeArquivo);
		fotoProfessor = professorRepository.salvarFoto(fotoProfessor);
		professorRepository.flush();
		
		NovaFoto novaFoto = NovaFoto.builder()
								.nomeArquivo(novoNomeArquivo)
								.inputStream(inputStream)
								.contentType(fotoProfessor.getContentType())
								.size(fotoProfessor.getTamanho())
								.build();
		
		fotoStorageService.substituir(novaFoto, nomeArquivoExistente);
		
		return fotoProfessor;
	}
	
	@Transactional
	public void excluirFotoDoProfessor(Long professorId) {
		FotoProfessor fotoProfessor = buscarFotoProfessor(professorId);
		
		professorRepository.excluirFoto(fotoProfessor);
		
		fotoStorageService.remover(fotoProfessor.getNomeArquivo());
	}
	
	public FotoAluno buscarFotoAluno(Long alunoId) {
		return alunoRepository.buscarFotoAluno(alunoId)
				.orElseThrow(() -> 
					new FotoPessoaNaoEncontradaException("Não existe uma foto para o aluno de id: "+alunoId));
	}
	
	public FotoProfessor buscarFotoProfessor(Long professorId) {
		return professorRepository.buscarFotoDoProfessor(professorId)
				.orElseThrow(() -> 
					new FotoPessoaNaoEncontradaException("Não existe uma foto para o professor de id: "+professorId));
	}
}