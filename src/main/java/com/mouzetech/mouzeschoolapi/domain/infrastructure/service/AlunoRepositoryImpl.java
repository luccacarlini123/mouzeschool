package com.mouzetech.mouzeschoolapi.domain.infrastructure.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.mouzetech.mouzeschoolapi.domain.model.FotoAluno;
import com.mouzetech.mouzeschoolapi.domain.repository.CatalogoFotoAlunoQueries;

@Repository
public class AlunoRepositoryImpl implements CatalogoFotoAlunoQueries {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Transactional
	@Override
	public FotoAluno salvarFoto(FotoAluno fotoAluno) {
		return entityManager.merge(fotoAluno);
	}

	@Transactional
	@Override
	public void excluirFoto(FotoAluno fotoAluno) {
		entityManager.remove(fotoAluno);	
	}

}
