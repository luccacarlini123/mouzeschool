package com.mouzetech.mouzeschoolapi.domain.repository;

import com.mouzetech.mouzeschoolapi.domain.model.FotoProfessor;

public interface CatalogoFotoProfessorQueries {
	
	FotoProfessor salvarFoto(FotoProfessor fotoProfessor);
	
	void excluirFoto(FotoProfessor fotoProfessor);

}