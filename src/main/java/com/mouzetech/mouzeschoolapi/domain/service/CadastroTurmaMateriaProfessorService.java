package com.mouzetech.mouzeschoolapi.domain.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.mouzetech.mouzeschoolapi.api.model.output.AssociacaoProfessorTurmaModel;
import com.mouzetech.mouzeschoolapi.api.model.output.GradeCurricularModel;
import com.mouzetech.mouzeschoolapi.domain.model.Materia;
import com.mouzetech.mouzeschoolapi.domain.model.Turma;
import com.mouzetech.mouzeschoolapi.domain.model.TurmaMateriaProfessor;
import com.mouzetech.mouzeschoolapi.domain.repository.TurmaMateriaProfessorRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CadastroTurmaMateriaProfessorService {

	private TurmaMateriaProfessorRepository turmaMateriaProfessorRepository;
	
	public boolean existeProfessorAlocadoEmMateriaDaTurma(Materia materia, Turma turma) {
		return turmaMateriaProfessorRepository.existeProfessorAlocadoNaMateriaDaTurma(materia.getId(), turma.getId()) > 0;
	}	
	
	public boolean isMateriaAssociadaNaTurma(Materia materia, Turma turma) {
		return turmaMateriaProfessorRepository.existeMateriaAssociadaNaTurma(materia.getId(), turma.getId()) > 0;
	}
	
	public GradeCurricularModel buscarGradeCurricularPorTurma(Turma turma) {
		List<TurmaMateriaProfessor> gradeCurricular = this.turmaMateriaProfessorRepository.buscarGradeCurricularPorTurma(turma);
		
		GradeCurricularModel gradeCurricularModel = new GradeCurricularModel();
		gradeCurricularModel.setTurma(turma.getNome());
		
		gradeCurricular.forEach(grade -> {
			AssociacaoProfessorTurmaModel associacaoProfessorTurmaModel = new AssociacaoProfessorTurmaModel();
			String nomeProfessor = null;
			
			if(grade.getProfessor() != null && StringUtils.isNotBlank(grade.getProfessor().getNome())) {
				nomeProfessor = grade.getProfessor().getNome();
			}
			
			associacaoProfessorTurmaModel.setProfessor(nomeProfessor);
			associacaoProfessorTurmaModel.setMateria(grade.getTurmaMateriaProfessorId().getMateria().getNome());
			gradeCurricularModel.getGradeCurricular().add(associacaoProfessorTurmaModel);
		});
		
		return gradeCurricularModel;
	}
	
}