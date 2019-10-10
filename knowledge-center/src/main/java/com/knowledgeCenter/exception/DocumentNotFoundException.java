package com.knowledgeCenter.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class DocumentNotFoundException  extends RuntimeException{
	
	public DocumentNotFoundException(String msg) {
		super(msg);
	}
	
}
