package com.mouzetech.mouzeschoolapi.domain.infrastructure.service.query;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mouzetech.mouzeschoolapi.api.model.output.AlunoResumoModel;
import com.mouzetech.mouzeschoolapi.api.model.output.AlunosDaTurmaModel;
import com.mouzetech.mouzeschoolapi.domain.model.Turma;
import com.mouzetech.mouzeschoolapi.domain.service.CadastroTurmaService;
import com.mouzetech.mouzeschoolapi.domain.service.TurmaQueryService;
import com.mouzetech.mouzeschoolapi.mapper.AlunoModelMapper;

@Service
public class TurmaQueryServiceImpl implements TurmaQueryService {

	@Autowired
	private CadastroTurmaService cadastroTurmaService;
	
	@Autowired
	private AlunoModelMapper alunoModelMapper;
	
	@Override
	public AlunosDaTurmaModel buscarAlunosDaTurma(Long turmaId) {
		Turma turma = cadastroTurmaService.buscarPorId(turmaId);

		AlunosDaTurmaModel dto = new AlunosDaTurmaModel();
		dto.setNomeTurma(turma.getNome());
		
		List<AlunoResumoModel> alunosDaTurma = 
				alunoModelMapper.toCollectionAlunoResumoDTO(turma.getAlunos());
		
		dto.setAlunos(alunosDaTurma);
		
		return dto;
	}
}