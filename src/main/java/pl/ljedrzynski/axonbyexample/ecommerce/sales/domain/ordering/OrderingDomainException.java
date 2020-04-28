package pl.ljedrzynski.axonbyexample.ecommerce.sales.domain.ordering;

import pl.ljedrzynski.axonbyexample.ecommerce.shared.exceptions.DomainException;

public class OrderingDomainException extends DomainException {

    public OrderingDomainException(String aggregateId, String message) {
        super(aggregateId, message);
    }
}
