package com.rest.application;

import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
	
	@Bean
	public BeanConfig swaggerConfig() {		
		
        BeanConfig beanConfig = new BeanConfig();
        beanConfig.setVersion("1.0.2");
        beanConfig.setResourcePackage("com.rest.resource");
        beanConfig.setScan(true);
        return beanConfig;
    }
	
	@Bean
	public ApiListingResource apiListingResource() {
        return new ApiListingResource();
    }
    
    @Bean
    public SwaggerSerializers swaggerSerializers() {
        return new SwaggerSerializers();
    }

}
