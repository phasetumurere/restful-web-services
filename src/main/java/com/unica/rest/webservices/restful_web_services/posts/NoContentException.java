package com.unica.rest.webservices.restful_web_services.posts;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NO_CONTENT)
public class NoContentException extends RuntimeException{
    public NoContentException(String message) {
        super(message);
    }
}
