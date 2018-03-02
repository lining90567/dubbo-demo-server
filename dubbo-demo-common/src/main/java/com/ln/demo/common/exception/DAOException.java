package com.ln.demo.common.exception;

/**
 * dao异常类
 * 
 * @author Lining
 * @date 2017/11/15
 */
public class DAOException extends RuntimeException {

	private static final long serialVersionUID = -4537960897528403282L;

	public DAOException() {
	}

	public DAOException(String message) {
		super(message);
	}

	public DAOException(String message, Throwable cause) {
		super(message, cause);
	}

	public DAOException(Throwable cause) {
		super(cause);
	}

}
