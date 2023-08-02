package com.e_commerce_project.commerce.error;

public class PersonNotFoundException extends RuntimeException {

    public PersonNotFoundException(String massage) {
        super(massage);
    }

    public PersonNotFoundException(Throwable cause){
        super(cause);
    }

    public PersonNotFoundException(String massage, Throwable cause){
        super(massage,cause);
    }
}
