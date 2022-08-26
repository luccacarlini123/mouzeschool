package com.mouzetech.mouzeschoolapi.domain.exception;

public class FotoAlunoNaoEncontradaException extends EntidadeNaoEncontradaException {
	private static final long serialVersionUID = 1L;

	public FotoAlunoNaoEncontradaException(String mensagem) {
		super(mensagem);
	}
}