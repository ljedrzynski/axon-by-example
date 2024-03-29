package pl.ljedrzynski.axonbyexample.ecommerce.sales.domain.client;

import lombok.NoArgsConstructor;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;
import pl.ljedrzynski.axonbyexample.ecommerce.sales.domain.client.events.ClientCreatedEvent;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
@NoArgsConstructor
public class Client {

    @AggregateIdentifier
    private String id;

    private String name;


    public Client(String id, String name) {
        apply(new ClientCreatedEvent(id, name));
    }

    @EventSourcingHandler
    public void on(ClientCreatedEvent event) {
        this.id = event.getClientId();
        this.name = event.getClientName();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ClientData getSnapshot() {
        return new ClientData(id, name);
    }
}
