package com.mouzetech.mouzeschoolapi.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
	
	@Bean
	public ModelMapper modelMapper() {
		var modelMapper = new ModelMapper();
		
//		modelMapper.createTypeMap(Professor.class, ProfessorModel.class)
//			.<String>addMapping(professorSource -> professorSource.getMatricula().getCodigoMatricula(), 
//					(professorResponseDtoDest, value) -> professorResponseDtoDest.setMatricula(value));
		
//		modelMapper.createTypeMap(Aluno.class, AlunoModel.class)
//		.<String>addMapping(alunoSource -> alunoSource.getMatricula().getCodigoMatricula(), 
//				(alunoResponseDtoDest, value) -> alunoResponseDtoDest.setMatricula(value));
	
//		modelMapper.createTypeMap(CadastrarProfessorInput.class, Professor.class)
//			.addMappings(mapper -> {
//				mapper.<Long>skip(Professor::setId);
//				mapper.<Matricula>skip(Professor::setMatricula);
//			});
			
		return modelMapper;
	}
}