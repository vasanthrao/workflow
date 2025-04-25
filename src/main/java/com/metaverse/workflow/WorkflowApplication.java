package com.metaverse.workflow;

import com.metaverse.workflow.common.fileservice.StorageProperties;
import com.metaverse.workflow.common.fileservice.StorageService;
import com.metaverse.workflow.common.util.CommonUtil;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@OpenAPIDefinition(
		info = @Info(
				title = "Workflow API",
				version = "1.0",
				description = "API documentation for Workflow Service"
		)
)
@SpringBootApplication(scanBasePackages = "com.metaverse")
@EnableConfigurationProperties(StorageProperties.class)
public class WorkflowApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(WorkflowApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(WorkflowApplication.class);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("*");
			}
		};
	}



}
