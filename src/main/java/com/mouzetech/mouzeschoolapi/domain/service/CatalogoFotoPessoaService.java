package com.mouzetech.mouzeschoolapi.domain.service;

import java.io.InputStream;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mouzetech.mouzeschoolapi.domain.exception.FotoAlunoNaoEncontradaException;
import com.mouzetech.mouzeschoolapi.domain.model.FotoAluno;
import com.mouzetech.mouzeschoolapi.domain.repository.AlunoRepository;
import com.mouzetech.mouzeschoolapi.domain.service.FotoStorageService.NovaFoto;

@Service
public class CatalogoFotoPessoaService {
	
	@Autowired
	private AlunoRepository alunoRepository;
	
	@Autowired
	private FotoStorageService fotoStorageService;
	
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
	
	public void excluirFotoDoAluno(Long alunoId) {
		FotoAluno fotoAluno = buscarFotoAluno(alunoId);
		
		alunoRepository.excluirFoto(fotoAluno);
		alunoRepository.flush();
		
		fotoStorageService.remover(fotoAluno.getNomeArquivo());
	}
	
	public FotoAluno buscarFotoAluno(Long alunoId) {
		return alunoRepository.buscarFotoAluno(alunoId)
				.orElseThrow(() -> 
					new FotoAlunoNaoEncontradaException("Não existe uma foto para o aluno de id: "+alunoId));
	}	
}