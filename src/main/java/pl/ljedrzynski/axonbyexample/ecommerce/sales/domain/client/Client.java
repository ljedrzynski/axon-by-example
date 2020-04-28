package pl.ljedrzynski.axonbyexample.ecommerce.sales.domain.client;

import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;
import pl.ljedrzynski.axonbyexample.ecommerce.sales.application.commands.CreateClientCommand;
import pl.ljedrzynski.axonbyexample.ecommerce.sales.domain.client.events.ClientCreatedEvent;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
@NoArgsConstructor
public class Client {

    @AggregateIdentifier
    private String id;

    private String name;

    @CommandHandler
    public Client(CreateClientCommand cmd) {
        apply(new ClientCreatedEvent(cmd.getClientId(), cmd.getName()));
    }

    @EventSourcingHandler
    public void on(ClientCreatedEvent clientCreatedEvent) {
        this.id = clientCreatedEvent.getClientId();
        this.name = clientCreatedEvent.getClientName();
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
