package pl.ljedrzynski.axonbyexample.ecommerce.shared.exceptions;

public class DomainOperationException extends EcommerceRuntimeException {

    private final String aggregateId;

    public DomainOperationException(String aggregateId, String message) {
        super(message);
        this.aggregateId = aggregateId;
    }

    public DomainOperationException(String aggregateId, String message, Object... args) {
        super(message, args);
        this.aggregateId = aggregateId;
    }

    public String getAggregateId() {
        return aggregateId;
    }
}
