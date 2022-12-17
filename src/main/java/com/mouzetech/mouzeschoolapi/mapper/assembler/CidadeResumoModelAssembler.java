package com.mouzetech.mouzeschoolapi.mapper.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.mouzetech.mouzeschoolapi.api.model.input.CidadeInput;
import com.mouzetech.mouzeschoolapi.api.model.output.CidadeModel;
import com.mouzetech.mouzeschoolapi.api.model.output.CidadeResumoModel;
import com.mouzetech.mouzeschoolapi.domain.model.Cidade;
import com.mouzetech.mouzeschoolapi.domain.model.Estado;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class CidadeResumoModelAssembler {

	private ModelMapper modelMapper;
	
	public CidadeResumoModel toModel(Cidade cidade) {
		CidadeResumoModel model = new CidadeResumoModel();
		model.setId(cidade.getId());
		model.setNome(cidade.getNome());
		model.setEstado(cidade.getEstado().getNome());
		return model;
	}
	
	public List<CidadeResumoModel> toCollectionModel(List<Cidade> cidades){
		return cidades.stream()
				.map(cidade -> toModel(cidade))
				.collect(Collectors.toList());
	}
}