package com.mouzetech.mouzeschoolapi.domain.model.enumeration;

import lombok.Getter;

@Getter
public enum SerieEnsino {
	PRIMEIRO_ANO(1, "PRIMEIRO"),
	SEGUNDO_ANO(2, "SEGUNDO"),
	TERCEIRO_ANO(3, "TERCEIRO"),
	QUARTO_ANO(4, "QUARTO"),
	QUINTO_ANO(5, "QUINTO"),
	SEXTO_ANO(6, "SEXTO"),
	SETIMO_ANO(7, "SÉTIMO"),
	OITAVO_ANO(8, "OITAVO");
	
	private Integer cod;
	private String descricao;
	
	private SerieEnsino(Integer cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}
	
	public static SerieEnsino toEnum(Integer cod) {
		if(cod == null) {
			return null;
		}
		
		for(SerieEnsino serieEnsino : SerieEnsino.values()) {
			if(serieEnsino.getCod().equals(cod)) {
				return serieEnsino;
			}
		}
		return null;
	}
	
	public static String getDescricaoByEnum(SerieEnsino serieEnsino) {
		String descricao = "";
		for(SerieEnsino serie : SerieEnsino.values()) {
			if(serie.equals(serieEnsino)) {
				descricao = serie.getDescricao();
			}
		}
		return descricao;
	}
	
	public static SerieEnsino toEnum(String descricao) {
		if(descricao == null) {
			return null;
		}
		
		for(SerieEnsino serieEnsino : SerieEnsino.values()) {
			if(serieEnsino.getDescricao().equals(descricao)) {
				return serieEnsino;
			}
		}
		return null;
	}
}