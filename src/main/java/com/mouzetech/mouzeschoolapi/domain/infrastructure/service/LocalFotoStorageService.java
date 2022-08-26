package com.mouzetech.mouzeschoolapi.domain.infrastructure.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.flywaydb.core.internal.util.FileCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mouzetech.mouzeschoolapi.core.storage.StorageProperties;
import com.mouzetech.mouzeschoolapi.domain.exception.StorageException;
import com.mouzetech.mouzeschoolapi.domain.service.FotoStorageService;

@Service
public class LocalFotoStorageService implements FotoStorageService {

	@Autowired
	private StorageProperties storageProperties;
	
	@Override
	public FotoRecuperada recuperar(String nomeArquivo) {
		try {
			Path path = getArquivoPath(nomeArquivo);
			
			FotoRecuperada fotoRecuperada = FotoRecuperada.builder()
				.inputStream(Files.newInputStream(path))
				.build();
			
			return fotoRecuperada;
		} catch (IOException e) {
			throw new StorageException("Não foi possível recuperar o arquivo", e.getCause());
		}
		
	}
	
	@Override
	public void armazenar(NovaFoto novaFoto) {
		try {
			Path path = getArquivoPath(novaFoto.getNomeArquivo());
			FileCopyUtils.copy(novaFoto.getInputStream(), Files.newOutputStream(path));
		} catch (IOException e) {
			throw new StorageException("Não foi possível salvar o arquivo", e.getCause());
		}
	}
	
	@Override
	public void remover(String nomeArquivo) {
		try {
			Path path = getArquivoPath(nomeArquivo);
			Files.deleteIfExists(path);
		} catch (IOException e) {
			throw new StorageException("Não foi possível excluir o arquivo", e.getCause());
		}
		
	}
	
	private Path getArquivoPath(String nomeArquivo) {
		return storageProperties.getLocal().getDiretorioFotos()
				.resolve(Path.of(nomeArquivo));
	}
	
}
