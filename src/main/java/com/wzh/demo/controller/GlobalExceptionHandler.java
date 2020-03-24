package com.wzh.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class GlobalExceptionHandler  {
    public GlobalExceptionHandler() {
    }

    @ExceptionHandler({NoHandlerFoundException.class}) // 未
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity handle404Error(HttpServletRequest req, HttpServletResponse rsp, Exception e) throws Exception {
        return null;
    }

    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity handle500Error(HttpServletRequest req, HttpServletResponse rsp, Exception e) throws Exception {
        return null;
    }

    @ExceptionHandler({NullPointerException.class})
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity handleNullException(HttpServletRequest req, HttpServletResponse rsp, NullPointerException e) throws Exception {
        return null;
    }
}

