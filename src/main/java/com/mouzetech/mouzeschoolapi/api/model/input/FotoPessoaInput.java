package com.mouzetech.mouzeschoolapi.api.model.input;

import javax.validation.constraints.NotNull;

import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import com.mouzetech.mouzeschoolapi.validation.FileContentType;
import com.mouzetech.mouzeschoolapi.validation.FileSize;

import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FotoPessoaInput {

	@ApiParam(hidden = true)
	@NotNull
	@FileSize(max = "8000KB")
	@FileContentType(allowed = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
	private MultipartFile arquivo;
	
	@NotNull
	private String descricao;
	
}
