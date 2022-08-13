package com.mouzetech.mouzeschoolapi.domain.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.mouzetech.mouzeschoolapi.api.model.output.MediaAlunoModel;
import com.mouzetech.mouzeschoolapi.domain.exception.NegocioException;
import com.mouzetech.mouzeschoolapi.domain.model.Aluno;
import com.mouzetech.mouzeschoolapi.domain.model.Materia;
import com.mouzetech.mouzeschoolapi.domain.model.Nota;
import com.mouzetech.mouzeschoolapi.domain.model.Turma;
import com.mouzetech.mouzeschoolapi.domain.repository.NotaRepository;
import com.mouzetech.mouzeschoolapi.domain.repository.TurmaMateriaProfessorRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MediaAlunoService {

	private CadastroAlunoService cadastroAlunoService;
	private CadastroTurmaService cadastroTurmaService;
	private NotaRepository notaRepository;
	private TurmaMateriaProfessorRepository turmaMateriaProfessorRepository;
	
	public List<MediaAlunoModel> buscarMedias(Long alunoId, Long turmaId){
		List<Materia> materiasQueTem4Notas = new ArrayList<>();
		
		Aluno aluno = cadastroAlunoService.buscarPorId(alunoId);
		Turma turma = cadastroTurmaService.buscarPorId(turmaId);
		
		boolean turmaPossuiAluno =  turma.getAlunos().stream().anyMatch(alunoDaTurma -> alunoDaTurma.equals(aluno));
		
		if(!turmaPossuiAluno) {
			throw new NegocioException(String.format("O aluno %S não está matriculado na %s", aluno.getNome(), turma.getNome()) );
		}
		
		List<Materia> materiasDaTurma = turmaMateriaProfessorRepository.buscarMateriasDaTurma(turma.getId());
		
		List<Nota> notas = notaRepository.findByAlunoAndTurma(aluno, turma);
		
		notas = notas.stream().sorted(Comparator.comparing(Nota::getNomeMateria))
				.collect(Collectors.toList());
		
		definirMateriasParaCalcularMedia(materiasQueTem4Notas, materiasDaTurma, notas);
		
		List<MediaAlunoModel> mediasDoAluno = calcularMedia(materiasQueTem4Notas, aluno, turma);
		
		return mediasDoAluno;
	}

	private void definirMateriasParaCalcularMedia(List<Materia> materiasQueTem4Notas,
			List<Materia> materiasDaTurma, List<Nota> notas) {
		int possuiMateria = 0;
		String nomeMateriaDaTurma = null;
		for(int i=0; i<materiasDaTurma.size(); i++) {
			nomeMateriaDaTurma = materiasDaTurma.get(i).getNome();
			for(int j=0; j<notas.size(); j++) {
				if(notas.get(j).getNomeMateria().equals(nomeMateriaDaTurma)) {
					possuiMateria++;
				}
			}
			if(possuiMateria == 4) {
				materiasQueTem4Notas.add(materiasDaTurma.get(i));
				possuiMateria = 0;
			}
			possuiMateria = 0;
		}
	}
	
	private List<MediaAlunoModel> calcularMedia(List<Materia> materiasQueTem4Notas, 
			Aluno aluno, Turma turma) {
		List<MediaAlunoModel> mediasDoAluno = new ArrayList<>();
		
		materiasQueTem4Notas.forEach(materia -> {
			List<Nota> notasParaCalcularMedia = notaRepository.findByAlunoAndTurmaAndMateria(aluno, turma, materia);
			double totalValorNotas = notasParaCalcularMedia.stream().mapToDouble(nota -> nota.getValor()).sum();
			double mediaFinal = totalValorNotas / 4;
			
			MediaAlunoModel mediaAlunoModel = MediaAlunoModel.builder()
					.materia(materia.getNome())
					.media(mediaFinal)
					.build();
			
			mediasDoAluno.add(mediaAlunoModel);
		});
		
		return mediasDoAluno;
	}
	
}