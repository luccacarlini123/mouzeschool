package com.mouzetech.mouzeschoolapi.domain.model.enumeration;

import lombok.Getter;

@Getter
public enum GrauEnsino {
	ENSINO_FUNDAMENTAL(1, "ENSINO FUNDAMENTAL"),
	ENSINO_MEDIO(2, "ENSINO MÉDIO");
	
	private Integer cod;
	private String descricao;
	
	private GrauEnsino(Integer cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}
	
	public static GrauEnsino toEnum(Integer cod) {
		if(cod == null) {
			return null;
		}
		
		for(GrauEnsino grauEnsino : GrauEnsino.values()) {
			if(grauEnsino.getCod().equals(cod)) {
				return grauEnsino;
			}
		}
		return null;
	}
	
	public static String getDescricaoByEnum(GrauEnsino grauEnsino) {
		String descricao = "";
		for(GrauEnsino grau : GrauEnsino.values()) {
			if(grau.equals(grauEnsino)) {
				descricao = grau.getDescricao();
			}
		}
		return descricao;
	}
	
	public static GrauEnsino toEnum(String descricao) {
		if(descricao == null) {
			return null;
		}
		
		for(GrauEnsino grauEnsino : GrauEnsino.values()) {
			if(grauEnsino.getDescricao().equals(descricao)) {
				return grauEnsino;
			}
		}
		return null;
	}
}
