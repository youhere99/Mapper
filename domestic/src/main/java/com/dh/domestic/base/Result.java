package com.dh.domestic.base;

import java.io.Serializable;

/**
 * @author shenshuai
 * @Time：2018年5月11日 上午11:08:15
 * @version 1.0
 * 统一controller方法返回结果
 */
public class Result<T> implements Serializable {
	private int code;
	private String message;
	private T data;

	private Result(int code, String message, T data) {
		this.code = code;
		this.message = message;
		this.data = data;
	}

	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	public T getData() {
		return data;
	}
	//返回正确结果，没有message信息和data对象，调用此方法(一般用不到)
	public static <T> Result<T> success() {
		return new Result<T>(ResultCode.SUCCESS, null, null);
	}
	//返回正确结果，没有message信息，有data对象，调用此方法
	public static <T> Result<T> success(T data) {
		return success(data, "success");
	}
	//返回正确结果，有message信息和data对象，调用此方法
	public static <T> Result<T> success(T data, String message) {
		return new Result<T>(ResultCode.SUCCESS, message, data);
	}
	//返回的是错误结果，有错误代码和信息，调用此方法
	public static <T> Result<T> error(int code, String message) {
		if (code == ResultCode.SUCCESS) {
			code = -1;
		}
		return new Result<T>(code, message, null);
	}
}
