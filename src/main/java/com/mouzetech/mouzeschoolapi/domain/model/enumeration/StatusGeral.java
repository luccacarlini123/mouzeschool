package com.mouzetech.mouzeschoolapi.domain.model.enumeration;

public enum StatusGeral {

	ATIVADA(1, "Ativada"),
	DESATIVADA(2, "Desativada");
	
	private StatusGeral(int codigo, String nome) {
		this.codigo = codigo;
		this.descricao = nome;
	}
	
	private int codigo;
	private String descricao;
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String nome) {
		this.descricao = nome;
	}	
}