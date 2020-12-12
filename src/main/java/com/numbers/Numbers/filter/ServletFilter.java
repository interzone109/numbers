package com.numbers.Numbers.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse; 
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.numbers.Numbers.metric.MetricComponent;

/***
 * the servlet responsible for collecting metrics
 * 
 * */

@Configurable
public class ServletFilter implements Filter {
	
	@Autowired
	private MetricComponent metricComponent;
	
	

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		long start = System.currentTimeMillis();
 
		HttpServletResponse res = (HttpServletResponse) response;
		
		 if(metricComponent==null){
	            ServletContext servletContext = request.getServletContext();
	            WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
	            metricComponent = webApplicationContext.getBean(MetricComponent.class);
	        }
		
		
		
		int code = res.getStatus();
		int count =1;
		
		if(metricComponent.getSuccessAvarageSercvise().containsKey(code)) {
		count = metricComponent.getSuccessAvarageSercvise().get(code);
		count++;
		}
		metricComponent.getSuccessAvarageSercvise().put(code, count);
		metricComponent.getLatencyCount().addAndGet(1);

		 
		chain.doFilter(request, response);

		metricComponent.getLatencyTime().addAndGet(System.currentTimeMillis()-start);
	}


	
}
