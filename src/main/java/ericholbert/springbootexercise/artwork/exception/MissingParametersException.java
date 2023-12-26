package ericholbert.springbootexercise.artwork.exception;

import ericholbert.springbootexercise.artwork.Artwork;

import java.util.List;

public class MissingParametersException extends RuntimeException {

    public MissingParametersException(Artwork artwork) {
        super(getMessage(artwork));
    }

    private static String getMessage(Artwork artwork) {
        List<String> missingAttributes = artwork.getNullAttributes();
        StringBuilder sb = new StringBuilder("Could not create artwork because the following parameters are missing: ");
        for (int i = 0; i < missingAttributes.size(); i++) {
            if (i != 0) {
                sb.append(", ");
            }
            sb.append("'");
            sb.append(missingAttributes.get(i));
            sb.append("'");
        }
        return sb.toString();
    }
}
