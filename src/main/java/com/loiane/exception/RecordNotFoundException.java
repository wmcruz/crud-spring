package com.loiane.exception;

import java.io.Serializable;

public class RecordNotFoundException extends RuntimeException implements Serializable {

    public RecordNotFoundException(final Long id) {
        super("Registro não encontrado com o id " + id);
    }
}