package com.mouzetech.mouzeschoolapi.domain.exception;

public class TurmaNaoEncontradaException extends EntidadeNaoEncontradaException {
	private static final long serialVersionUID = 1L;

	public TurmaNaoEncontradaException(String mensagem) {
		super(mensagem);
	}
}