package br.com.cnpj.api.model.exception;

public class CNPJException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public CNPJException(String message) {
        super(message);
    }
}
