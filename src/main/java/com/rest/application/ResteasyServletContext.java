/*
 * Copyright 2015, LexisNexis.
 */

package com.rest.application;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

import org.jboss.resteasy.annotations.interception.ServerInterceptor;
import org.jboss.resteasy.spi.ResteasyProviderFactory;
import org.springframework.stereotype.Component;

@Component
@Provider
@ServerInterceptor
public class ResteasyServletContext implements ContainerRequestFilter, ContainerResponseFilter {

    @Inject 
    ServletContext sc;
    
    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
		ResteasyProviderFactory.getContextDataMap().put(ServletContext.class, sc);
    }

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext)
            throws IOException {
        
    }
}