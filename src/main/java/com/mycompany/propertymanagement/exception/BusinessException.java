package com.mycompany.propertymanagement.exception;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor //default constructor with no parameters
//Exception or error is an object and that object should be extending from Exception class.
public class BusinessException extends Exception{
    //as this business_exception can be thrown from any part of the applciation,
    //that's why we want Exception model class to be a part of it


    //
    private List<ErrorModel> error;

    //default constructor

    //constructor with a parameter
    public BusinessException (List<ErrorModel>error){
        this.error = error;
    }


}
