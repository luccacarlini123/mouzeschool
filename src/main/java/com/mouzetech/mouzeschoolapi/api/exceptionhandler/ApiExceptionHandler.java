package com.mouzetech.mouzeschoolapi.api.exceptionhandler;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.mouzetech.mouzeschoolapi.domain.exception.EntidadeNaoEncontradaException;
import com.mouzetech.mouzeschoolapi.domain.exception.NegocioException;

@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
	
	private static final String MSG_ERRO_USUARIO_FINAL = "Ocorreu um erro inesperado no sistema. Tente novamente mais tarde e se o erro persistir,"
			+ " entre em contato com o administrador do sistema";
	
	private static final String MSG_ERRO_DADOS_INVALIDOS = "Há campos que estão inválidos, corrija-os e tente novamente.";
	
	private static final String PARAMETRO_INVALIDO = "Há parâmeros de URL que estão inválidos, corrija-os e tente novamente. Parâmetro inválido: %s";
		
	@Autowired
	private MessageSource messageSource;

	@ExceptionHandler(EntidadeNaoEncontradaException.class)
	public ResponseEntity<?> handleEntidadeNaoEncontradaException(EntidadeNaoEncontradaException ex, WebRequest request){
		ProblemType type = ProblemType.RECURSO_NAO_ENCONTRADO;
		
		String detail = ex.getMessage();
		
		Problem problem = createFromProblemBuilder(HttpStatus.NOT_FOUND, type, detail)
				.userMessage(detail)
				.build();
		
		return handleExceptionInternal(ex, problem, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}
	
	@ExceptionHandler(NegocioException.class)
	public ResponseEntity<?> handleNegocioException(NegocioException ex, WebRequest request){
		ProblemType type = ProblemType.ERRO_NEGOCIO;
		
		String detail = ex.getLocalizedMessage();
		String userMessage = ex.getMessage();
		
		Problem problem = createFromProblemBuilder(HttpStatus.BAD_REQUEST, type, detail)
				.userMessage(userMessage)
				.build();
		
		return handleExceptionInternal(ex, problem, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		ProblemType type = ProblemType.DADOS_INVALIDOS;
		
		Problem problem = createFromProblemBuilder(status, type, String.format(PARAMETRO_INVALIDO, ex.getValue()) ).build();
				
		
		return handleExceptionInternal(ex, problem, headers, status, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		List<Problem.Object> objects = ex.getBindingResult().getFieldErrors()
				.stream()
				.map(error -> {
					String message = messageSource.getMessage(error, LocaleContextHolder.getLocale());
					
					String field = error.getField();
					
					return Problem.Object.builder()
							.objectName(field)
							.userMessage(message)
							.build();
				}).collect(Collectors.toList());
		
		
		Problem problem = createFromProblemBuilder(status, ProblemType.DADOS_INVALIDOS, MSG_ERRO_DADOS_INVALIDOS)
				.problemObjects(objects)
				.build();
		
		return handleExceptionInternal(ex, problem, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		ProblemType type = ProblemType.ERRO_NEGOCIO;
		
		Problem problem = createFromProblemBuilder(status, type, ex.getMessage())
				.userMessage(MSG_ERRO_DADOS_INVALIDOS)
				.build();
		
		return handleExceptionInternal(ex, problem, headers, status, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		if(body == null) {
			body = Problem.builder()
						.status(status.value())
						.date(OffsetDateTime.now())
						.title(status.getReasonPhrase())
						.userMessage(MSG_ERRO_USUARIO_FINAL)
						.build();
		} else if(body instanceof String) {
			body = Problem.builder()
					.status(status.value())
					.date(OffsetDateTime.now())
					.title((String) body)
					.userMessage(MSG_ERRO_USUARIO_FINAL)
					.build();
		}
		return super.handleExceptionInternal(ex, body, headers, status, request);
	}	
	
	private Problem.ProblemBuilder createFromProblemBuilder(HttpStatus status, ProblemType type, String detail){
		return Problem.builder()
				.status(status.value())
				.type(type.getUri())
				.title(type.getTitle())
				.detail(detail)
				.date(OffsetDateTime.now());
	}
	
}
