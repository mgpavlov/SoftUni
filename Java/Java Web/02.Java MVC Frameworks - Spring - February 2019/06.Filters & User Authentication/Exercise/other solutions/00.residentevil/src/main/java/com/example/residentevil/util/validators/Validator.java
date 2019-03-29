package com.example.residentevil.util.validators;

public interface Validator {
    <M> boolean isValid(M model);
}
