package com.shpapth.githubrepolister.api.exception;

import lombok.Getter;

@Getter
public class InvalidParameterException extends RuntimeException {

	private final String resourceName;

	private final String msg;

	public InvalidParameterException(String resourceName, String msg) {
		super();
		this.resourceName = resourceName;
		this.msg = msg;
	}
}
