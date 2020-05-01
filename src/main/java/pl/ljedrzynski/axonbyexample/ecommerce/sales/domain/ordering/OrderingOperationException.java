package pl.ljedrzynski.axonbyexample.ecommerce.sales.domain.ordering;

import pl.ljedrzynski.axonbyexample.ecommerce.shared.exceptions.DomainOperationException;

public class OrderingOperationException extends DomainOperationException {

    public OrderingOperationException(String aggregateId, String message) {
        super(aggregateId, message);
    }

    public OrderingOperationException(String aggregateId, String message, Object... args) {
        super(aggregateId, message, args);
    }
}
