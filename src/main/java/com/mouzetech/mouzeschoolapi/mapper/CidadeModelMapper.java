package com.mouzetech.mouzeschoolapi.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.mouzetech.mouzeschoolapi.api.model.input.CadastroCidadeInput;
import com.mouzetech.mouzeschoolapi.api.model.output.CidadeModel;
import com.mouzetech.mouzeschoolapi.api.model.output.CidadeResumoModel;
import com.mouzetech.mouzeschoolapi.domain.model.Cidade;
import com.mouzetech.mouzeschoolapi.domain.model.Estado;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class CidadeModelMapper {

	private ModelMapper modelMapper;
	
	public CidadeModel toCidadeModel(Cidade cidade) {
		return modelMapper.map(cidade, CidadeModel.class);
	}
	
	public List<CidadeModel> toCollectionCidadeModel(List<Cidade> cidades){
		return cidades.stream()
				.map(cidade -> toCidadeModel(cidade))
				.collect(Collectors.toList());
	}
	
	public Cidade fromCadastroCidadeInput(CadastroCidadeInput input) {
		Cidade cidade = new Cidade();
		cidade.setNome(input.getNome());
		cidade.setEstado(new Estado());
		cidade.getEstado().setId(input.getEstadoId());
		return cidade;
	}
	
	public CidadeResumoModel toCidadeResumoModel(Cidade cidade) {
		CidadeResumoModel model = new CidadeResumoModel();
		model.setId(cidade.getId());
		model.setNome(cidade.getNome());
		model.setEstado(cidade.getEstado().getNome());
		return model;
	}
	
	public List<CidadeResumoModel> toCollectionCidadeResumoModel(List<Cidade> cidades){
		return cidades.stream()
				.map(cidade -> toCidadeResumoModel(cidade))
				.collect(Collectors.toList());
	}
}