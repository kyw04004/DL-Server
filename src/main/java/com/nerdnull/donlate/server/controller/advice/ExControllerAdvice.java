package com.nerdnull.donlate.server.controller.advice;

import com.nerdnull.donlate.server.controller.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice(basePackages="com.nerdnull.donlate.server.controller")
public class ExControllerAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler
    public Response illegalArgumentExHandler(IllegalArgumentException e) {
        log.error(e.getMessage(), e);
        return Response.error(Response.BAD_REQUEST, e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler
    public Response illegalAccessExHandler(IllegalAccessException e) {
        log.error(e.getMessage(), e);
        return Response.error(Response.BAD_REQUEST, e.getMessage());
    }


    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler
    public Response exHandler(Exception e) {
        log.error(e.getMessage(), e);
        return Response.error(Response.INTERNAL_SERVER_ERROR, e.getMessage());
    }
}
