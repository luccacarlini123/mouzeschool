package com.mouzetech.mouzeschoolapi.domain.exception;

public class StorageException extends RuntimeException {

	private static final long serialVersionUID = -8210049927566932038L;

	public StorageException(String mensagem) {
		super(mensagem);
	}
	
	public StorageException(String mensagem, Throwable throwable) {
		super(mensagem, throwable);
	}
}