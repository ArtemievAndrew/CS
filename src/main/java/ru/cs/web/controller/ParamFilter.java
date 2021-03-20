package ru.cs.web.controller;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ru.cs.service.SessionParam;

@Component
public class ParamFilter implements Filter {

	Logger logger = LoggerFactory.getLogger(ParamFilter.class);
	
	@Autowired
	SessionParam  sessionParam;
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
	
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		
		String uri = httpServletRequest.getRequestURI();
		
		//Пропускаем из фильтра
		if (
			uri.startsWith("/css/")||
			uri.startsWith("/js/")||
			uri.startsWith("/image/")||
			uri.equals("/favicon.ico")||
			uri.equals("/login")||
			uri.equals("/param")
		) {
			chain.doFilter(httpServletRequest, httpServletResponse);
		} else {

			//Если не выбраны параметры, то заставим выбрать
			if (sessionParam==null|| sessionParam.getFirmId()==null || sessionParam.getFirmId()==0) {
				logger.info("Redirect to param for: " + uri);
				httpServletResponse.sendRedirect("/param");
			} else {
				chain.doFilter(httpServletRequest, httpServletResponse);
			}
		}
		
	}

	@Override
	public void destroy() {
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

}