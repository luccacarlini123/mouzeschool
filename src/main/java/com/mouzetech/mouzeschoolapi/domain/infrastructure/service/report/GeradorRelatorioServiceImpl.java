package com.mouzetech.mouzeschoolapi.domain.infrastructure.service.report;

import java.util.Collection;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.mouzetech.mouzeschoolapi.domain.exception.ReportException;
import com.mouzetech.mouzeschoolapi.domain.service.GeradorRelatorioService;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class GeradorRelatorioServiceImpl implements GeradorRelatorioService {

	@Override
	public byte[] gerar(Map<String, Object> parametros, String path, Collection<?> dados) {
		try {		
			var inputStream = this.getClass().getResourceAsStream(path);
			
			var dataSource = new JRBeanCollectionDataSource(dados);
			
			var jasperPrint = JasperFillManager.fillReport(inputStream, parametros, dataSource);
			
			return JasperExportManager.exportReportToPdf(jasperPrint);
		} catch (Exception e) {
			throw new ReportException("Não foi possível emitir o relatório", e.getCause());
		}
	}
	
}
