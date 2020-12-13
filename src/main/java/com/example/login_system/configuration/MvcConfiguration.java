package com.example.login_system.configuration;

import com.example.login_system.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfiguration {

    @Bean
    public WebMvcConfigurer webMvcConfigurer(){
        return new WebMvcConfigurer() {
            @Override
            public void addInterceptors(InterceptorRegistry registry) {

                registry.addInterceptor(new LoginInterceptor())
                        .addPathPatterns("/*")
                        .excludePathPatterns("/","/login","/user","/forget","/getVerify","/checkVerify");

            }

            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/").setViewName("index");
                registry.addViewController("/welcome").setViewName("welcome");
                registry.addViewController("changePassword").setViewName("change");
            }
        };
    }
}
