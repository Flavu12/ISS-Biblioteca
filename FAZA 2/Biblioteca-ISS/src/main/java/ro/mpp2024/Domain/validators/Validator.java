package ro.mpp2024.Domain.validators;

public interface Validator<E> {
    void validate(E entity) throws ValidationException;
}
