package com.mouzetech.mouzeschoolapi.validation;

import java.util.Arrays;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.web.multipart.MultipartFile;

public class FileContentTypeValidator implements ConstraintValidator<FileContentType, MultipartFile> {

	private String[] allowed;
	
	@Override
	public void initialize(FileContentType constraintAnnotation) {
		allowed = constraintAnnotation.allowed();
	}
	
	@Override
	public boolean isValid(MultipartFile value, ConstraintValidatorContext context) {
		return value == null 
				|| Arrays.stream(allowed).anyMatch(permitido -> permitido.equals(value.getContentType()));
	}

}
