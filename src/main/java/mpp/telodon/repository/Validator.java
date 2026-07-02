package mpp.telodon.repository;

public interface Validator<E> {
    void validate(E entity) throws ValidationException;
}