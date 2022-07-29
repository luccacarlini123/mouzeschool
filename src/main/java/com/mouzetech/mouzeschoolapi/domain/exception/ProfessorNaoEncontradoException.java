package com.mouzetech.mouzeschoolapi.domain.exception;

public class ProfessorNaoEncontradoException extends EntidadeNaoEncontradaException {
	private static final long serialVersionUID = 1L;

	public ProfessorNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
}