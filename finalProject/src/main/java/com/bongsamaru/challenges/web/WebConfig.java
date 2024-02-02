package com.bongsamaru.challenges.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private ChallengeInfoInterceptor facilityInfoInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(facilityInfoInterceptor)
                .addPathPatterns("/challenge/**");  // "/facilityInfo/**" 경로에만 적용
    }
    
    @Value("${file.loading.path}")
    String uploadPath;


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
       
    	// addResourceHandler("매핑 경로")를 적어둔다. localhost:8080/upload/ 로 들어오는 모든 정적 리소스 요청을 static폴더가 아닌 .addResourceLoactions에 적어둔 경로로 부터 찾아준다.
    	registry.addResourceHandler("/upload/**").addResourceLocations(uploadPath);
    }
}