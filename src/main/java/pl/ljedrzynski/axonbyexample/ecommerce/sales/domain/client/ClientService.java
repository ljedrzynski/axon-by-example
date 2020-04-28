package pl.ljedrzynski.axonbyexample.ecommerce.sales.domain.client;

import lombok.RequiredArgsConstructor;
import org.axonframework.modelling.command.Repository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final Repository<Client> clientRepository;

    public ClientData getClientById(String id) {
        return clientRepository.load(id).invoke(Client::getSnapshot);
    }

}
