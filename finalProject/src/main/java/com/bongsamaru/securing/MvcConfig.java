package com.bongsamaru.securing;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		WebMvcConfigurer.super.addViewControllers(registry);
		registry.addViewController("/home").setViewName("home");
		registry.addViewController("/").setViewName("layout");
		registry.addViewController("/hello").setViewName("hello");
		registry.addViewController("/admin/adminMain").setViewName("admin/adminMain");
		registry.addViewController("/login").setViewName("login");
		registry.addViewController("/admin/ui-buttons").setViewName("admin/ui-buttons");
		registry.addViewController("/meeting").setViewName("meeting");
	}

}