package com.mouzetech.mouzeschoolapi.core.email;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@Getter
@Setter
@ConfigurationProperties("mouzeschool.email")
public class EmailProperties {

	private String remetente;
	
}