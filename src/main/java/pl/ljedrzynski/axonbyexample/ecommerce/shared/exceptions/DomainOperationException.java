package pl.ljedrzynski.axonbyexample.ecommerce.shared.exceptions;

public class DomainOperationException extends RuntimeException {
    String aggregateId;

    public DomainOperationException(String aggregateId, String message) {
        super(message);
        this.aggregateId = aggregateId;
    }

    public DomainOperationException(String aggregateId, String message, Object... args) {
        super(String.format(message, args));
        this.aggregateId = aggregateId;
    }

    public String getAggregateId() {
        return aggregateId;
    }
}
