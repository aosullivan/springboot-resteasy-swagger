package com.rest.application;

/*
 * Copyright 2015, LexisNexis.
 */


import org.jboss.resteasy.plugins.spring.SpringBeanProcessor;
import org.jboss.resteasy.spi.ResteasyDeployment;
import org.jboss.resteasy.spi.ResteasyProviderFactory;
import org.jboss.resteasy.springmvc.ResteasyHandlerAdapter;
import org.jboss.resteasy.springmvc.ResteasyHandlerMapping;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

@ConditionalOnClass(ResteasyHandlerMapping.class)
@EnableConfigurationProperties
@Configuration
public class ResteasyAutoConfiguration {
		
	@ConditionalOnMissingBean(ResteasyDeployment.class)
	@ConfigurationProperties(prefix="resteasy.deployment")
	@Bean(initMethod="start", destroyMethod="stop")
	public ResteasyDeployment resteasyDeployment(final SpringBeanProcessor springBeanProcessor) {
		ResteasyDeployment resteasyDeployment = new ResteasyDeployment() {
			public void start() {
				super.start();
				if (springBeanProcessor.getRegistry() == null) {
					springBeanProcessor.setRegistry(this.getRegistry());
				}
			}
		};
		resteasyDeployment.setProviderFactory(springBeanProcessor.getProviderFactory());
		return resteasyDeployment;
	}

	@ConditionalOnMissingBean(SpringBeanProcessor.class)
	@Bean
	public SpringBeanProcessor springBeanProcessor() {
		SpringBeanProcessor springBeanProcessor = new SpringBeanProcessor();
		springBeanProcessor.setProviderFactory(new ResteasyProviderFactory());
		return springBeanProcessor;
	}

	@ConditionalOnMissingBean(ResteasyHandlerMapping.class)
	@Bean
	public ResteasyHandlerMapping resteasyHandlerMapper(ResteasyDeployment deployment) {
		ResteasyHandlerMapping handlerMapping = new ResteasyHandlerMapping(deployment);
		handlerMapping.setOrder(Ordered.LOWEST_PRECEDENCE - 10);
		return handlerMapping;
	}

	@ConditionalOnMissingBean(ResteasyHandlerAdapter.class)
	@Bean
	public ResteasyHandlerAdapter resteasyHandlerAdapter(ResteasyDeployment deployment) {
		return new ResteasyHandlerAdapter(deployment);
	}
	
}
