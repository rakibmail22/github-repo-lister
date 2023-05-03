package com.shpapth.githubrepolister.api.model;

import java.io.Serial;
import java.io.Serializable;

public record ErrorResponse(String resource, String message) implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;
}
