package com.mouzetech.mouzeschoolapi.mapper.assembler;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.mouzetech.mouzeschoolapi.api.model.output.RelacaoNotasDaTurmaModel;
import com.mouzetech.mouzeschoolapi.domain.model.Nota;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class RelacaoNotasDaTurmaModelAssembler {

	private ModelMapper modelMapper;
	
	public List<RelacaoNotasDaTurmaModel> toCollectionModel(List<Nota> notas){
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