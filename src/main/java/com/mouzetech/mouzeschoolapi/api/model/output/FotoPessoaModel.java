package com.mouzetech.mouzeschoolapi.api.model.output;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FotoPessoaModel {
	private String nomeArquivo;
	private String descricao;
	private String contentType;
	private Long tamanho;	
}