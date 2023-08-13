package com.project.cities.exception;

public class CityAlreadyExistException extends RuntimeException {
    public CityAlreadyExistException(String message) {
        super(message);
    }
}
