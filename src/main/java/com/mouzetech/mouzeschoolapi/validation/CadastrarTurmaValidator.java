package com.mouzetech.mouzeschoolapi.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.mouzetech.mouzeschoolapi.api.exceptionhandler.Problem;
import com.mouzetech.mouzeschoolapi.api.model.input.CadastrarTurmaInput;
import com.mouzetech.mouzeschoolapi.domain.exception.NegocioException;
import com.mouzetech.mouzeschoolapi.domain.model.enumeration.GrauEnsino;
import com.mouzetech.mouzeschoolapi.domain.model.enumeration.SerieEnsino;
import com.mouzetech.mouzeschoolapi.domain.repository.TurmaRepository;

public class CadastrarTurmaValidator implements ConstraintValidator<CadastrarTurma, CadastrarTurmaInput> {
	
	@Autowired
	private TurmaRepository turmaRepository;
	
	@Override
	public void initialize(CadastrarTurma ann) {
	}
	
	@Override
	public boolean isValid(CadastrarTurmaInput dto, ConstraintValidatorContext context) {
		List<Problem.Object> list = new ArrayList<>();
		
		if(StringUtils.isNotBlank(dto.getGrauEnsino()) && GrauEnsino.toEnum(dto.getGrauEnsino()) == null) {
			list.add(
					Problem.Object
						.builder()
						.objectName("grauEnsino")
						.userMessage("Grau de ensino inválido")
						.build());
		}
		
		if(StringUtils.isNotBlank(dto.getSerieEnsino()) && SerieEnsino.toEnum(dto.getSerieEnsino()) == null) {
			list.add(
					Problem.Object
						.builder()
						.objectName("serieEnsino")
						.userMessage("Série de ensino inválida")
						.build());
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
		
		if(turmaRepository.findByNomeAndGrauEnsinoAndSerieEnsino(
				dto.getNome(), GrauEnsino.toEnum(dto.getGrauEnsino()), SerieEnsino.toEnum(dto.getSerieEnsino()))
				.isPresent()) {
			throw new NegocioException("Já existe uma turma com esse nome");
		}
		
		for(Problem.Object object : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(object.getUserMessage()).addPropertyNode(object.getObjectName())
				.addConstraintViolation();
		}
		
		return list.isEmpty();
	}

}
