package com.niit.automobileapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "No Document Found with Given Id")
public class NoDocumentException extends Exception{ }
