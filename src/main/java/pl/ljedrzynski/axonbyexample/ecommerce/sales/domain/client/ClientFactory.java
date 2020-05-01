package pl.ljedrzynski.axonbyexample.ecommerce.sales.domain.client;

import org.springframework.stereotype.Component;

@Component
public class ClientFactory {

    public Client createClient(ClientData clientData) {
        return new Client(clientData.getId(), clientData.getName());
    }
}
