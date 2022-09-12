package com.mouzetech.mouzeschoolapi.domain.service;

import java.util.Collection;
import java.util.Map;

public interface GeradorRelatorioService {
	
	byte[] gerar(Map<String, Object> parametros, String path, Collection<?> dados);	

}