package pl.ljedrzynski.axonbyexample.ecommerce.sales.domain.client.events;

import lombok.Value;

@Value
public class ClientCreatedEvent {

    String clientId;
    String clientName;
}
