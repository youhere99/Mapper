package com.dh.domestic.config;

/**
 * 
 * Title.
 * <p>
 * Description.应用异常
 * <p>
 * Copyright: Copyright (c) 2018年5月22日 下午2:38:45
 * <p>
 * Company:
 * <p>
 * 
 * @author zhaomingxing
 * @version 1.8
 */
public class ApplicationException extends RuntimeException {

  private static final long serialVersionUID = -2678203134198782909L;

  public static final String MESSAGE = "应用异常";

  protected int code = ResultCode.APPLICATION_ERROR;

  public ApplicationException() {
    super(MESSAGE);
  }

  public ApplicationException(String message) {
    super(message);
  }

  public ApplicationException(int code, String message) {
    super(message);
    this.code = code;
  }

  public ApplicationException(String message, Throwable cause) {
    super(message, cause);
  }

  public ApplicationException(int code, String message, Throwable cause) {
    super(message, cause);
    this.code = code;
  }

  public ApplicationException(Throwable cause) {
    super(cause);
  }

  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }
}

