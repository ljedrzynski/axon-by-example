package pl.ljedrzynski.axonbyexample.ecommerce.shared.exceptions;

import pl.ljedrzynski.axonbyexample.ecommerce.shared.cannonicalmodel.AggregateId;

public class DomainException extends RuntimeException {
    private String aggregateId;

    public DomainException(String aggregateId, String message) {
        super(message);
        this.aggregateId = aggregateId;
    }

    public String getAggregateId() {
        return aggregateId;
    }
}
