package com.mouzetech.mouzeschoolapi.mapper.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.mouzetech.mouzeschoolapi.api.controller.NotaController;
import com.mouzetech.mouzeschoolapi.api.model.output.NotasDaTurmaModel;
import com.mouzetech.mouzeschoolapi.domain.model.Nota;

@Component
public class NotasDaTurmaModelAssembler extends RepresentationModelAssemblerSupport<Nota, NotasDaTurmaModel> {

	public NotasDaTurmaModelAssembler() {
		super(NotaController.class, NotasDaTurmaModel.class);
	}

	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public NotasDaTurmaModel toModel(Nota nota) {
		NotasDaTurmaModel notasDaTurmaModel = modelMapper.map(nota, NotasDaTurmaModel.class);
		
		return notasDaTurmaModel;
	}
	
	@Override
	public CollectionModel<NotasDaTurmaModel> toCollectionModel(Iterable<? extends Nota> entities) {
		return super.toCollectionModel(entities);
	}	
}