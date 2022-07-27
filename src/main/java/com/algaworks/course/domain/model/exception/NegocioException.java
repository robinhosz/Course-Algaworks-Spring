package com.algaworks.course.domain.model.exception;

public class NegocioException extends RuntimeException{

    public NegocioException (String msg) {
        super(msg);
    }

}
