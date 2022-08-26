package com.mouzetech.mouzeschoolapi.domain.repository;

import com.mouzetech.mouzeschoolapi.domain.model.FotoAluno;

public interface CatalogoFotoAlunoQueries {
	
	FotoAluno salvarFoto(FotoAluno fotoAluno);
	
	void excluirFoto(FotoAluno fotoAluno);

}