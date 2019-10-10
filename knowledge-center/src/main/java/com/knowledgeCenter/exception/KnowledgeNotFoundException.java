package com.knowledgeCenter.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class KnowledgeNotFoundException extends RuntimeException {

	public KnowledgeNotFoundException(String msg) {
		super(msg);
	}

}
