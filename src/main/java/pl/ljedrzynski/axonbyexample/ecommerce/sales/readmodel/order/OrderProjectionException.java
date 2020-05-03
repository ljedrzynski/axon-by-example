package pl.ljedrzynski.axonbyexample.ecommerce.sales.readmodel.order;

import pl.ljedrzynski.axonbyexample.ecommerce.shared.exceptions.EcommerceRuntimeException;

public class OrderProjectionException extends EcommerceRuntimeException {

    public OrderProjectionException(String message, Object... args) {
        super(message, args);
    }
}
