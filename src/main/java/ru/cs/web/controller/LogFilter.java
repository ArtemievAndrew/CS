package ru.cs.web.controller;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class LogFilter implements Filter {

	Logger logger = LoggerFactory.getLogger(LogFilter.class);

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;

		String uri = httpServletRequest.getRequestURI();

		// Пропускаем простое из логирования
		if (!(uri.startsWith("/css/") || 
				uri.startsWith("/js/") || 
				uri.startsWith("/image/") || 
				uri.equals("/favicon.ico"))) {

			logger.info("IP:{} request {} from {}", 
					httpServletRequest.getRemoteAddr(), 
					httpServletRequest.getMethod(),
					httpServletRequest.getRequestURI());

		}

		chain.doFilter(httpServletRequest, httpServletResponse);

	}
}
