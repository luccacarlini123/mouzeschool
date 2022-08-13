package com.mouzetech.mouzeschoolapi.api.model.output;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MediaAlunoModel {
	private String materia;
	private Double media;
}