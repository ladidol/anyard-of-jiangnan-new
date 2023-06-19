package org.cuit.fhzheng.common.exception;

import org.cuit.fhzheng.common.response.ResponseEnum;

/**
 * @author
 * @date 2020/7/11
 */
public class anyardOfJiangnanException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private Object object;

	private ResponseEnum responseEnum;

	public anyardOfJiangnanException(String msg) {
		super(msg);
	}

	public anyardOfJiangnanException(String msg, Object object) {
		super(msg);
		this.object = object;
	}

	public anyardOfJiangnanException(String msg, Throwable cause) {
		super(msg, cause);
	}


	public anyardOfJiangnanException(ResponseEnum responseEnum) {
		super(responseEnum.getMsg());
		this.responseEnum = responseEnum;
	}

	public anyardOfJiangnanException(ResponseEnum responseEnum, Object object) {
		super(responseEnum.getMsg());
		this.responseEnum = responseEnum;
		this.object = object;
	}


	public Object getObject() {
		return object;
	}

	public ResponseEnum getResponseEnum() {
		return responseEnum;
	}

}
