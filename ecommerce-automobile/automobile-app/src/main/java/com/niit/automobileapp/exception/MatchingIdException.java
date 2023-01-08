package com.niit.automobileapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.CONFLICT,reason = "Duplicate Data!")
public class MatchingIdException extends Exception {

}
