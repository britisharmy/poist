package com.boilerplate.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;


import org.springframework.context.annotation.Bean;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;


@Configuration
@EnableWebMvc
@ComponentScan (basePackages = "com.boilerplate")
public class AppConfig extends WebMvcConfigurerAdapter{
	

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry
			.addMapping("/**")
				.allowedOrigins(/**origins**/)
				.allowedMethods("*")
				.allowedHeaders("*")
				.exposedHeaders("Access-Control-Allow-Origin", 
						"Access-Control-Allow-Methods", 
						"Access-Control-Allow-Headers", 
						"Access-Control-Max-Age", 
						"Access-Control-Request-Headers", 
						"Access-Control-Request-Method");
	}
    
    
	
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		registry.viewResolver(viewResolver);
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/assets/**").addResourceLocations("/assets/");
	}
	
	
	@Override
	public void configurePathMatch(PathMatchConfigurer matcher) {
		matcher.setUseRegisteredSuffixPatternMatch(true);
	}
	
	@Bean
	public HttpSessionEventPublisher httpSessionEventPublisher() {
		return new HttpSessionEventPublisher();
	}
	

}
