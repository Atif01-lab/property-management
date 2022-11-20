package com.mycompany.propertymanagement.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
//exceptions will be thrown by other classes and will be handled in this class
@ControllerAdvice //used for global exception handling
public class CustomExceptionHandler {

    @ExceptionHandler(BusinessException.class) //if BusinessException error is thrown from anywhere in the application,
    //then the below code will be executed.
    public ResponseEntity<List<ErrorModel>> handleBusinessException(BusinessException bex){
        System.out.println("BusinessException is thrown");
        return new ResponseEntity<List<ErrorModel>>(bex.getError(), HttpStatus.BAD_REQUEST);
    }

}
