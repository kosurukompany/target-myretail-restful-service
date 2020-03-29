package com.target.casestudy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.target.casestudy.common.Constants;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Satya Kosuru
 *
 */

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select().apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.regex(Constants.PRODUCTS_URL + ".*")).build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title(Constants.SWAGGER_TITLE).description(Constants.SWAGGER_DESCRIPTION)
				.contact(new Contact(Constants.AUTHOR_NAME, Constants.AUTHOR_WEBSITE, Constants.AUTHOR_EMAIL))
				.license(Constants.MIT).licenseUrl(Constants.LICENSE_URL).version(Constants.SWAGGER_API_VERSION)
				.build();
	}
}
