package ericholbert.springbootexercise.artwork.exception;

public class InvalidSortPropertyException extends RuntimeException {

    public InvalidSortPropertyException(String sortProperty) {
        super("Could not sort by '%s' because it is not an Artwork field".formatted(sortProperty));
    }
}
