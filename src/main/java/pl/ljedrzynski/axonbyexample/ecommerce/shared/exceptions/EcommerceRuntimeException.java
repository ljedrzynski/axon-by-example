package pl.ljedrzynski.axonbyexample.ecommerce.shared.exceptions;

import static java.lang.String.format;

public class EcommerceRuntimeException extends RuntimeException {

    public EcommerceRuntimeException(String message) {
        super(message);
    }

    public EcommerceRuntimeException(String message, Object... args) {
        super(format(message, args));
    }
}
