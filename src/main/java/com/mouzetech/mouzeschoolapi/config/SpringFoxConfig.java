package com.mouzetech.mouzeschoolapi.config;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.fasterxml.classmate.TypeResolver;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.mouzetech.mouzeschoolapi.api.exceptionhandler.Problem;
import com.mouzetech.mouzeschoolapi.api.model.output.ResumoAlunoModel;
import com.mouzetech.mouzeschoolapi.openapi.model.PageableModelOpenApi;
import com.mouzetech.mouzeschoolapi.openapi.model.PagedResumoAlunoModel;
import com.mouzetech.mouzeschoolapi.openapi.model.ProblemaInternalServerErrorOpenApi;
import com.mouzetech.mouzeschoolapi.openapi.model.ProblemaNotFoundOpenApi;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RepresentationBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseBuilder;
import springfox.documentation.schema.AlternateTypeRules;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Response;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.json.JacksonModuleRegistrar;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SpringFoxConfig implements WebMvcConfigurer {

	@Bean
	public Docket apiDocker() {
		var typeResolver = new TypeResolver(); 
		
		return new Docket(DocumentationType.OAS_30)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.mouzetech.mouzeschoolapi"))
				.paths(PathSelectors.any())
				.build()
				.useDefaultResponseMessages(false)
				.globalResponses(HttpMethod.GET, globalGetResponseMessages())
				.globalResponses(HttpMethod.POST, globalPostResponseMessages())
				.globalResponses(HttpMethod.PUT, globalPutResponseMessages())
				.globalResponses(HttpMethod.DELETE, globalDeleteResponseMessages())
				.additionalModels(typeResolver.resolve(Problem.class))
				.additionalModels(typeResolver.resolve(ProblemaNotFoundOpenApi.class))
				.additionalModels(typeResolver.resolve(ProblemaInternalServerErrorOpenApi.class))
				.directModelSubstitute(Pageable.class, PageableModelOpenApi.class)
				.alternateTypeRules(AlternateTypeRules.newRule(typeResolver.resolve(Page.class, ResumoAlunoModel.class), PagedResumoAlunoModel.class))
			.apiInfo(apiInfo())
			.tags(new Tag("Alunos", "Gerencia os alunos"))
			.tags(new Tag("Professores", "Gerencia os professores"))
			.tags(new Tag("Email", "Envio de email"))
			.tags(new Tag("Notas", "Gerencia as notas dos alunos"))
			.tags(new Tag("Matérias", "Gerencia as matérias"))
			.tags(new Tag("Médias", "Gerencia média das notas dos alunos"))
			.tags(new Tag("Relatórios", "Gera relatórios"))
			.tags(new Tag("Turmas", "Gerencia as turmas"));
	}
	
	private List<Response> globalGetResponseMessages(){
		return Arrays.asList(
					new ResponseBuilder()
						.code(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
						.description("Erro interno do servidor")
						.representation(MediaType.APPLICATION_JSON)
						.apply(getProblemaInternalServerErrorModelReference())
						.build(),
					
					new ResponseBuilder()
						.code(String.valueOf(HttpStatus.NOT_ACCEPTABLE.value()))
						.description("O tipo da representação do recurso retornado não é compatível com o esperado pelo requisitante")
						.build(),
						
					new ResponseBuilder()
						.code(String.valueOf(HttpStatus.NOT_FOUND.value()))
						.description("Recurso inexistente")
						.representation(MediaType.APPLICATION_JSON)
						.apply(getProblemaNotFoundModelReference())
						.build()
				);
	}
	
	private List<Response> globalPostResponseMessages(){
		return Arrays.asList(
					new ResponseBuilder()
						.code(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
						.description("Erro interno do servidor")
						.representation(MediaType.APPLICATION_JSON)
						.apply(getProblemaInternalServerErrorModelReference())
						.build(),
					
					new ResponseBuilder()
						.code(String.valueOf(HttpStatus.NOT_ACCEPTABLE.value()))
						.description("O tipo da representação do recurso retornado não é compatível com o esperado pelo requisitante")
						.build(),
						
					new ResponseBuilder()
						.code(String.valueOf(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value()))
						.description("O corpo da requisição não está no formato esperado pelo servidor.")
						.representation(MediaType.APPLICATION_JSON)
						.apply(getProblemaModelReference())
						.build(),
						
					new ResponseBuilder()
						.code(String.valueOf(HttpStatus.BAD_REQUEST.value()))
						.description("Houve um erro no processamento da requisição por culpa do requisitante")
						.representation(MediaType.APPLICATION_JSON)
						.apply(getProblemaModelReference())
						.build()
				);
	}
	
	private List<Response> globalPutResponseMessages(){
		return Arrays.asList(
					new ResponseBuilder()
						.code(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
						.description("Erro interno do servidor")
						.representation(MediaType.APPLICATION_JSON)
						.apply(getProblemaInternalServerErrorModelReference())
						.build(),
					
					new ResponseBuilder()
						.code(String.valueOf(HttpStatus.NOT_ACCEPTABLE.value()))
						.description("O tipo da representação do recurso retornado não é compatível com o esperado pelo requisitante")
						.build(),
						
					new ResponseBuilder()
						.code(String.valueOf(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value()))
						.description("O corpo da requisição não está no formato esperado pelo servidor.")
						.representation(MediaType.APPLICATION_JSON)
						.apply(getProblemaModelReference())
						.build(),
						
					new ResponseBuilder()
						.code(String.valueOf(HttpStatus.BAD_REQUEST.value()))
						.description("Houve um erro no processamento da requisição por culpa do requisitante")
						.representation(MediaType.APPLICATION_JSON)
						.apply(getProblemaModelReference())
						.build(),
						
					new ResponseBuilder()
						.code(String.valueOf(HttpStatus.NOT_FOUND.value()))
						.description("Recurso inexistente")
						.representation(MediaType.APPLICATION_JSON)
						.apply(getProblemaNotFoundModelReference())
						.build()
				);
	}
	
	private List<Response> globalDeleteResponseMessages(){
		return Arrays.asList(
					new ResponseBuilder()
						.code(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
						.description("Erro interno do servidor")
						.representation(MediaType.APPLICATION_JSON)
						.apply(getProblemaInternalServerErrorModelReference())
						.build(),
						
					new ResponseBuilder()
						.code(String.valueOf(HttpStatus.BAD_REQUEST.value()))
						.description("Houve um erro no processamento da requisição por culpa do requisitante")
						.representation(MediaType.APPLICATION_JSON)
						.apply(getProblemaModelReference())
						.build(),
						
					new ResponseBuilder()
						.code(String.valueOf(HttpStatus.NOT_FOUND.value()))
						.description("Recurso inexistente")
						.representation(MediaType.APPLICATION_JSON)
						.apply(getProblemaNotFoundModelReference())
						.build()
				);
	}
	
	public ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("MouzeSchool API")
				.version("1")
				.description("API para gerenciamento escolar")
				.contact(new Contact("MouzeTech", "https://www.mouzetech.com.br", "contato@mouzetech.com.br"))
				.build();
	}
	
	private Consumer<RepresentationBuilder> getProblemaModelReference() {
	    return r -> r.model(m -> m.name("Problema")
	            .referenceModel(ref -> ref.key(k -> k.qualifiedModelName(
	                    q -> q.name("Problema").namespace("com.mouzetech.mouzeschoolapi.api.exceptionhandler")))));
	}
	
	private Consumer<RepresentationBuilder> getProblemaInternalServerErrorModelReference() {
	    return r -> r.model(m -> m.name("ProblemaInternalServerErrorOpenApi")
	            .referenceModel(ref -> ref.key(k -> k.qualifiedModelName(
	                    q -> q.name("ProblemaInternalServerErrorOpenApi").namespace("com.mouzetech.mouzeschoolapi.openapi.model")))));
	}
	
	private Consumer<RepresentationBuilder> getProblemaNotFoundModelReference() {
	    return r -> r.model(m -> m.name("ProblemaNotFoundOpenApi")
	            .referenceModel(ref -> ref.key(k -> k.qualifiedModelName(
	                    q -> q.name("ProblemaNotFoundOpenApi").namespace("com.mouzetech.mouzeschoolapi.openapi.model")))));
	}
	
	@Bean
	public JacksonModuleRegistrar springFoxJacksonConfig() {
		return objectMapper -> objectMapper.registerModule(new JavaTimeModule());
	}
	
}