package com.numbers.Numbers.config;

import javax.servlet.Filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.numbers.Numbers.filter.ServletFilter;

/**
 * servlet filter configuration
 *
 */
@Configuration
public class ConfigApp {

	 @Bean
	 public FilterRegistrationBean<ServletFilter> loggingFilter(){
	     FilterRegistrationBean<ServletFilter> registrationBean 
	       = new FilterRegistrationBean<>();
	         
	     registrationBean.setFilter(new ServletFilter());
	     registrationBean.addUrlPatterns("/number/*");
	         
	     return registrationBean;    
	 }
	 
	 public Filter someServletFilter() {
		    return new ServletFilter();
		}
}
