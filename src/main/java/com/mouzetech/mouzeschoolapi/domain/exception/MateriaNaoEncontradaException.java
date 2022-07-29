package com.mouzetech.mouzeschoolapi.domain.exception;

public class MateriaNaoEncontradaException extends EntidadeNaoEncontradaException {
	private static final long serialVersionUID = 1L;

	public MateriaNaoEncontradaException(String mensagem) {
		super(mensagem);
	}
}