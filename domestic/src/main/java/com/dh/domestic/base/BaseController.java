package com.dh.domestic.base;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.dh.domestic.config.ResultCode;

public class BaseController {

	public String getUserId(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		return username;

	}

	public String getHost(HttpServletRequest request) {
		String secheme = request.getScheme();
		String localName = request.getLocalName();
		Integer localPort = request.getLocalPort();
		String contextPath = request.getContextPath();
		String host = secheme + "://" + localName + ":" + localPort;
		if (StringUtils.isNotBlank(contextPath)) {
			host += host + "/" + contextPath;
		}
		return host;

	}

	/**
	 * 
	 * 功能：单个参数校验
	 * 
	 * @param cve
	 * @return 2018年5月25日下午4:19:54 zhaomingxing
	 */
	@ExceptionHandler(ConstraintViolationException.class)
	public Result<Object> handleConstraintViolationException(ConstraintViolationException cve) {
		Set<ConstraintViolation<?>> cves = cve.getConstraintViolations();
		for (ConstraintViolation<?> constraintViolation : cves) {
			return Result.error(ResultCode.VALIDATE_ERROR, constraintViolation.getMessage());
		}
		return Result.success();
	}

	/**
	 * 
	 * 功能：对象校验
	 * 
	 * @param bindingResult
	 * @return 2018年5月25日下午4:31:05 zhaomingxing
	 */
	public Result<Object> validatedEntity(BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			for (FieldError fieldError : bindingResult.getFieldErrors()) {
				return Result.error(ResultCode.VALIDATE_ERROR, fieldError.getDefaultMessage());
			}
		}
		return null;
	}
}
