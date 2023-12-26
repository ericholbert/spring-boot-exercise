package ericholbert.springbootexercise.artwork.exception;

public class ArtworkNotFoundException extends RuntimeException {

    public ArtworkNotFoundException(Long id) {
        super("Could not find artwork with id '%s'".formatted(id));
    }

    public ArtworkNotFoundException(String institution, String invNum) {
        super("Could not find artwork deposited in '%s' with inventory number '%s'".formatted(institution, invNum));
    }
}
