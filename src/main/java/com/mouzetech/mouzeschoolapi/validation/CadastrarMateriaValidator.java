package com.mouzetech.mouzeschoolapi.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.mouzetech.mouzeschoolapi.api.exceptionhandler.Problem;
import com.mouzetech.mouzeschoolapi.api.model.input.CadastrarMateriaInput;
import com.mouzetech.mouzeschoolapi.domain.exception.NegocioException;
import com.mouzetech.mouzeschoolapi.domain.model.enumeration.GrauEnsino;
import com.mouzetech.mouzeschoolapi.domain.model.enumeration.SerieEnsino;
import com.mouzetech.mouzeschoolapi.domain.repository.MateriaRepository;

public class CadastrarMateriaValidator implements ConstraintValidator<CadastrarMateria, CadastrarMateriaInput> {
	
	@Autowired
	private MateriaRepository materiaRepository;
	
	@Override
	public void initialize(CadastrarMateria ann) {
	}
	
	@Override
	public boolean isValid(CadastrarMateriaInput dto, ConstraintValidatorContext context) {
		List<Problem.Object> list = new ArrayList<>();
		
		if(StringUtils.isNotBlank(dto.getGrauEnsino()) && GrauEnsino.toEnum(dto.getGrauEnsino()) == null) {
			list.add(Problem.Object.builder().objectName("grauEnsino").userMessage("Grau de ensino inválido").build());
		}
		
		if(StringUtils.isNotBlank(dto.getSerieEnsino()) && SerieEnsino.toEnum(dto.getSerieEnsino()) == null) {
			list.add(Problem.Object.builder().objectName("serieEnsino").userMessage("Série de ensino inválido").build());
		}
		
		if(GrauEnsino.ENSINO_MEDIO.equals(GrauEnsino.toEnum(dto.getGrauEnsino())) 
				&& SerieEnsino.toEnum(dto.getSerieEnsino()) != null
				&& !(SerieEnsino.PRIMEIRO_ANO.equals(SerieEnsino.toEnum(dto.getSerieEnsino())))
						&& !SerieEnsino.SEGUNDO_ANO.equals(SerieEnsino.toEnum(dto.getSerieEnsino()))
						&& !SerieEnsino.TERCEIRO_ANO.equals(SerieEnsino.toEnum(dto.getSerieEnsino()))) {
			list.add(Problem.Object
						.builder()
						.objectName("serieEnsino")
						.userMessage("O ensino médio vai apenas do primeiro ao terceiro ano")
						.build());
		}
		
		if(materiaRepository.findByNomeAndGrauEnsinoAndSerieEnsino(
				dto.getNome(), GrauEnsino.toEnum(dto.getGrauEnsino()), SerieEnsino.toEnum(dto.getSerieEnsino()))
				.isPresent()) {
			throw new NegocioException("Essa matéria já existe");
		}
		
		list.stream().forEach(object -> {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(object.getUserMessage()).addPropertyNode(object.getObjectName())
				.addConstraintViolation();
		});
		return list.isEmpty();
	}

}
