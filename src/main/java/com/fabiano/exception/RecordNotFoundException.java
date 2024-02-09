package com.fabiano.exception;

public class RecordNotFoundException extends RuntimeException{

    public RecordNotFoundException(Long id){
        super("Registro nao encontrado com o id: "+id);
    }

}
