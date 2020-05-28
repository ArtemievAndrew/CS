package ru.cs.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SimpleControllerConfig implements WebMvcConfigurer {
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/test_output").setViewName("test_output");
		
		registry.addViewController("/ledger").setViewName("under_construction");
		registry.addViewController("/rep_status").setViewName("under_construction");
		registry.addViewController("/rep_outcome").setViewName("under_construction");
		registry.addViewController("/rep_income").setViewName("under_construction");
		registry.addViewController("/rep_ledger").setViewName("under_construction");
		registry.addViewController("/date").setViewName("under_construction");
		
	}
}
