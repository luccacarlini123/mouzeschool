package com.mouzetech.mouzeschoolapi.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = CadastrarMateriaValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CadastrarMateria {
	String message() default "Erro de validação";
	Class<?>[] groups() default { };
	Class<? extends Payload>[] payload() default { };
}
