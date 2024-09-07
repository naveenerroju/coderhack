package com.naveen.coderhack.exception;

import com.naveen.coderhack.model.Error;

public class BusinessException extends RuntimeException {

    public BusinessException(Error message){
        super(String.valueOf(message));
    }

}
