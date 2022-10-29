package com.mouzetech.mouzeschoolapi.api.model.output;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FotoPessoaModel {
	
	@ApiModelProperty(example = "c5c72c2a-9f03-4caa-b560-9c9203c9414e-nyjah.jpg")
	private String nomeArquivo;
	
	@ApiModelProperty(example = "Foto do Lucca")
	private String descricao;
	
	@ApiModelProperty(example = "image/jpeg")
	private String contentType;
	
	@ApiModelProperty(example = "38563")
	private Long tamanho;	
}