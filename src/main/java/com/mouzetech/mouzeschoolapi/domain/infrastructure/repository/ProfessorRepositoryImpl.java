package com.mouzetech.mouzeschoolapi.domain.infrastructure.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mouzetech.mouzeschoolapi.domain.model.FotoProfessor;
import com.mouzetech.mouzeschoolapi.domain.repository.CatalogoFotoProfessorQueries;

@Repository
public class ProfessorRepositoryImpl implements CatalogoFotoProfessorQueries {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Transactional
	@Override
	public FotoProfessor salvarFoto(FotoProfessor fotoProfessor) {
		return entityManager.merge(fotoProfessor);
	}

	@Transactional
	@Override
	public void excluirFoto(FotoProfessor fotoProfessor) {
		entityManager.remove(fotoProfessor);
	}

}