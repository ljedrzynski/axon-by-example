package pl.ljedrzynski.axonbyexample.ecommerce.sales.domain.client;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.axonframework.modelling.command.Repository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final Repository<Client> clientRepository;
    private final ClientFactory clientFactory;

    @SneakyThrows
    public void createNewClient(ClientData clientData) {
        clientRepository.newInstance(() -> clientFactory.createClient(clientData));
    }

    public ClientData getClientData(String id) {
        return clientRepository.load(id)
                .invoke(Client::getSnapshot);
    }
}
