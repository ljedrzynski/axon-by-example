package pl.ljedrzynski.axonbyexample.ecommerce.shared.cannonicalmodel;

import lombok.Value;
import org.apache.commons.lang3.Validate;

import java.io.Serializable;
import java.util.UUID;

@Value
public class AggregateId implements Serializable{

    String aggregateId;

    public AggregateId(String aggregateId) {
        Validate.notNull(aggregateId);
        this.aggregateId = aggregateId;
    }

    public static AggregateId generate(){
        return new AggregateId(UUID.randomUUID().toString());
    }

    @Override
    public String toString() {
        return aggregateId;
    }
}
