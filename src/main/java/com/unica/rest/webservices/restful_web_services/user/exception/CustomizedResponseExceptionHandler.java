package com.unica.rest.webservices.restful_web_services.user.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice // Applied to all the controllers
@RestController
public class CustomizedResponseExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request){
        ExceptionResponse exceptionResponse =
                new ExceptionResponse(new Date(), ex.getMessage(),
                        request.getDescription(false));
        return new ResponseEntity(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<Object> userNotFound(UserNotFoundException ex, WebRequest request){
        ExceptionResponse exceptionResponse =
                new ExceptionResponse(new Date(), ex.getMessage(),
                        request.getDescription(false));
        return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);
    }
//    @ExceptionHandler(NoContentException.class)
//    public final ResponseEntity<Object> noContent(NoContentException ex, WebRequest request){
//        ExceptionResponse exceptionResponse =
//                new ExceptionResponse(new Date(), ex.getMessage(),
//                        request.getDescription(false));
//        return new ResponseEntity(exceptionResponse, HttpStatus.NO_CONTENT);
//    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers,
            HttpStatusCode status, WebRequest request) {

//        ExceptionResponse exceptionResponse =
//                new ExceptionResponse(new Date(), "Validation Failed",
        List<String> errorMessages=ex.getBindingResult()
                                .getFieldErrors()
                                .stream()
                                .map(fieldError -> fieldError.getField()+ ": "+fieldError.getDefaultMessage())
                                .collect(Collectors.toList());
                
        return new ResponseEntity(errorMessages, HttpStatus.BAD_REQUEST);
//        return this.handleExceptionInternal(ex, (Object)null, headers, status, request);
        //ex.getBondingResult is the function that holds information of what went wrong so we made it to string
    }
}
