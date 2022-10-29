package com.mouzetech.mouzeschoolapi.api.model.input;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoInput {

	@ApiModelProperty(example = "26600000", required = true)
	@NotBlank
	private String cep;
	
	@ApiModelProperty(example = "Rua Feliciana dos Anjos Teixeira", required = true)
	@NotBlank
	private String logradouro;
	
	@ApiModelProperty(example = "582", required = true)
	@NotBlank
	private String numero;
	
	@ApiModelProperty(example = "Casa próxima a antiga olaria")
	private String complemento;
	
	@ApiModelProperty(example = "Sabugo", required = true)
	@NotBlank
	private String bairro;
	
	@ApiModelProperty(example = "1", required = true)
	@NotNull
	private Long cidadeId;
	
}
