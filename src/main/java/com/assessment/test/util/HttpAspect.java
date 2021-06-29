package com.assessment.test.util;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.google.gson.Gson;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
@Order(1)
public class HttpAspect {

	private final static Logger logger = LoggerFactory.getLogger(HttpAspect.class);
	@Autowired
	private Gson gson;

	@Before("execution(public * com.assessment.test.controller.*.*(..))")

	public void logBefore(JoinPoint joinPoint) {

		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();

		// This is the information to record the http request.
		logger.info("\n\n===================== REQUEST ========================");
		// URL
		logger.info("URL = {}", request.getRequestURL());
		// METHOD
		logger.info("METHOD = {}", request.getMethod());
		// CLASS_METHOD
		logger.info("CLASS_METHOD = {}",
				joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
		// ARGS
		logger.info("ARGS = {}", gson.toJson(joinPoint.getArgs()));

		logger.info("\n==============================================================\n\n");

	}

	@AfterReturning(value = "execution(public * com.assessment.test.controller.*.*(..))", returning = "object")
	public void logAfterReturn(Object object) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();

		// This is the information returned by the record http request.
		logger.info("\n\n===================== RESPONSE ========================");
		// URL
		logger.info("URL = {}", request.getRequestURL());
		// METHOD
		logger.info("METHOD = {}", request.getMethod());

		// RESPONSE
		logger.info("RESPONSE = {}", gson.toJson(object));

		logger.info("\n==============================================================\n\n");
	}
}