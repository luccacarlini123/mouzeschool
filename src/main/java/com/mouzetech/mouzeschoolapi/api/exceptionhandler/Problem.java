package com.mouzetech.mouzeschoolapi.api.exceptionhandler;

import java.time.OffsetDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Getter;

@ApiModel(value = "Problema")
@JsonInclude(Include.NON_NULL)
@Builder
@Getter
public class Problem {

	private Integer status;
	private String type;
	private String title;
	private String detail;
	
	private String userMessage;
	private OffsetDateTime date;
	
	private List<Object> problemObjects;
	
	@Getter
	@Builder
	public static class Object {
		private String objectName;
		private String userMessage;
	}
}