package ro.mpp2024.Domain.validators;

public class ValidationException extends RuntimeException{
    public ValidationException(String msg) { super(msg); }
}
