package com.mouzetech.mouzeschoolapi.domain.exception;

public class AlunoNaoEncontradoException extends EntidadeNaoEncontradaException {
	private static final long serialVersionUID = 1L;

	public AlunoNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
}