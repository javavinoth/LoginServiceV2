package com.apibucket.loginapi.config;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.apibucket.loginapi.security.JwtUserTokens;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerTwoLoginApiConfig {

	private static final Set<String> DEFAULT_PRODUCES_AND_CONSUMES = new HashSet<String>(
			Arrays.asList("application/json"));

	@Bean
	public Docket api() {
		Class[] clazz= {JwtUserTokens.class};

		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(getApiInfo()).produces(DEFAULT_PRODUCES_AND_CONSUMES).ignoredParameterTypes(clazz)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.apibucket.loginapi.controller"))
	            .paths(PathSelectors.any()).build();

	}

	private ApiInfo getApiInfo() {

		return new ApiInfoBuilder().title("Login Api").description("Enhanced login security based on roles API")
				.contact(new Contact("vinoth", "www.apibucket.in", "vinoth.vsv95@gmail.com")).license("Apache 2.0")
				.licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html").version("1.0.0").build();
	}
}
