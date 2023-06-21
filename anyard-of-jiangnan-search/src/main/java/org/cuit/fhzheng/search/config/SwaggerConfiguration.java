package org.cuit.fhzheng.search.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Swagger文档，只有在测试环境才会使用
 *
 * @author
 */
@Configuration
@EnableKnife4j
public class SwaggerConfiguration {


	@Bean
	public GroupedOpenApi publicApi() {
		return GroupedOpenApi.builder()
				.group("search服务")
				.packagesToScan("org.cuit.fhzheng.search.controller")
				.pathsToMatch("/**")
				.build();
	}

	@Bean
	public OpenAPI springShopOpenAPI() {
		return new OpenAPI()
				.info(new Info().title("anyard-of-jiangnan商城接口文档")
						.description("anyard-of-jiangnan商城接口文档Swagger版")
						.version("v0.0.1")
						.license(new License().name("使用请遵守商用授权协议").url("https://gitee.com/fhzheng/anyard-of-jiangnan")));
	}
}
