package com.rest.application;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;

import org.jboss.resteasy.annotations.interception.ServerInterceptor;
import org.jboss.resteasy.spi.ResteasyProviderFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Component;

@ConditionalOnBean(ServletContext.class)
@Component
@Provider
@ServerInterceptor
public class ResteasyServletContext implements ContainerRequestFilter {

    @Inject 
    ServletContext sc;
    
    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
		ResteasyProviderFactory.getContextDataMap().put(ServletContext.class, sc);
    }

}