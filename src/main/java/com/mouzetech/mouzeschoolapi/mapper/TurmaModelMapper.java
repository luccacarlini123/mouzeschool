package com.mouzetech.mouzeschoolapi.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.mouzetech.mouzeschoolapi.api.model.input.CadastrarTurmaInput;
import com.mouzetech.mouzeschoolapi.api.model.output.ResumoTurmaModel;
import com.mouzetech.mouzeschoolapi.api.model.output.TurmaModel;
import com.mouzetech.mouzeschoolapi.domain.model.Turma;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class TurmaModelMapper {

	private ModelMapper modelMapper;
	
	public Turma toEntity(CadastrarTurmaInput dto) {
		return modelMapper.map(dto, Turma.class);
	}
	
	public TurmaModel toModel(Turma turma) {
		return modelMapper.map(turma, TurmaModel.class);
	}
	
	public ResumoTurmaModel toResumoModel(Turma turma) {
		return modelMapper.map(turma, ResumoTurmaModel.class);
	}
	
	public List<ResumoTurmaModel> toCollectionResumoModel(List<Turma> turmas){
		return turmas.stream()
				.map(turma -> toResumoModel(turma))	
				.collect(Collectors.toList());
	}
}