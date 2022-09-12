package com.mouzetech.mouzeschoolapi.domain.infrastructure.service.report;

import java.util.HashMap;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mouzetech.mouzeschoolapi.domain.model.dto.AlunosDaTurmaDTO;
import com.mouzetech.mouzeschoolapi.domain.service.GeradorRelatorioService;
import com.mouzetech.mouzeschoolapi.domain.service.TurmaQueryService;
import com.mouzetech.mouzeschoolapi.domain.service.TurmaReportService;

@Service
public class TurmaReportServiceImpl implements TurmaReportService {

	private static final String PATH_RELATORIO_ALUNOS_DA_TURMA = "/relatorios/alunos-da-turma.jasper";

	@Autowired
	private TurmaQueryService turmaQueryService;
	
	@Autowired
	private GeradorRelatorioService geradorRelatorioService;
	
	@Override
	public byte[] emitirRelatorioAlunosDaTurma(Long turmaId) {
		AlunosDaTurmaDTO dto = turmaQueryService.buscarAlunosDaTurma(turmaId);
		
		var parametros = new HashMap<String, Object>();
		parametros.put("REPORT_LOCALE", new Locale("pt", "BR"));
		parametros.put("nomeTurma", dto.getNomeTurma());
		
		return geradorRelatorioService.gerar(parametros, PATH_RELATORIO_ALUNOS_DA_TURMA, dto.getAlunos());
	}

}