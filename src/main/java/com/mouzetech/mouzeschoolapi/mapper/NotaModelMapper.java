package com.mouzetech.mouzeschoolapi.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.mouzetech.mouzeschoolapi.api.model.output.NotasDaTurmaModel;
import com.mouzetech.mouzeschoolapi.api.model.output.RelacaoNotasDaTurmaModel;
import com.mouzetech.mouzeschoolapi.domain.model.Nota;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class NotaModelMapper {

	private ModelMapper modelMapper;
	
	public NotasDaTurmaModel toNotaDTO(Nota nota) {
		return modelMapper.map(nota, NotasDaTurmaModel.class);
	}
	
	public List<NotasDaTurmaModel> toCollectionDTO(List<Nota> notas){
		return notas.stream()
					.map(nota -> toNotaDTO(nota))
					.collect(Collectors.toList());
	}
	
	public List<RelacaoNotasDaTurmaModel> toRelacaoNotasDaTurmaModelCollection(List<Nota> notas){
		List<RelacaoNotasDaTurmaModel> notasModel = new ArrayList<>();
		
		notas.forEach(nota -> {
			RelacaoNotasDaTurmaModel relacaoNotasDaTurmaModel = new RelacaoNotasDaTurmaModel();
			relacaoNotasDaTurmaModel.setNomeAluno(nota.getAluno().getNome());
			relacaoNotasDaTurmaModel.setBimestre(nota.getBimestre());
			relacaoNotasDaTurmaModel.setNomeMateria(nota.getMateria().getNome());
			relacaoNotasDaTurmaModel.setValor(nota.getValor());
			notasModel.add(relacaoNotasDaTurmaModel);
		});
		return notasModel;
	}
	
}