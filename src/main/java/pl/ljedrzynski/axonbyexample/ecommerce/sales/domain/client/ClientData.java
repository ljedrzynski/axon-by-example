package pl.ljedrzynski.axonbyexample.ecommerce.sales.domain.client;

import lombok.AllArgsConstructor;
import lombok.Value;

@AllArgsConstructor
@Value
public class ClientData {

    String id;
    String name;


    public ClientData(ClientData clientData) {
        this.id = clientData.id;
        this.name = clientData.name;
    }
}
