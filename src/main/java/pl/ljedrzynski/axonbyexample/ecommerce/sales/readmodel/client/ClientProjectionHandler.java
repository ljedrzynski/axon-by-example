package pl.ljedrzynski.axonbyexample.ecommerce.sales.readmodel.client;

import lombok.RequiredArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;
import pl.ljedrzynski.axonbyexample.ecommerce.sales.domain.client.events.ClientCreatedEvent;

@Component
@RequiredArgsConstructor
public class ClientProjectionHandler {

    private final ClientReadModelRepository clientReadModelRepository;

    @EventHandler
    private void on(ClientCreatedEvent event) {
        var clientSummary = new ClientReadModel(event.getClientId(), event.getClientName());
        clientReadModelRepository.save(clientSummary);
    }
}
