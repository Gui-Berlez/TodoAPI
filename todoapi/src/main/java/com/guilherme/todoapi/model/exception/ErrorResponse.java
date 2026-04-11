package com.guilherme.todoapi.model.exception;

public class ErrorResponse {

    private int status;
    private String message;

    public ErrorResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }
    public String getMessage() {
        return message;
    }
    //Essa classe é simples — ela só representa o formato do JSON de erro que queremos retornar.

}
