package com.mouzetech.mouzeschoolapi.domain.exception;

public class FotoPessoaNaoEncontradaException extends EntidadeNaoEncontradaException {
	private static final long serialVersionUID = 1L;

	public FotoPessoaNaoEncontradaException(String mensagem) {
		super(mensagem);
	}
}