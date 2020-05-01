package pl.ljedrzynski.axonbyexample.ecommerce.sales.domain.ordering;

import org.springframework.stereotype.Component;
import pl.ljedrzynski.axonbyexample.ecommerce.sales.domain.client.ClientData;

import java.time.LocalDateTime;

@Component
public class OrderFactory {

    public Order create(String orderId, ClientData clientData) {
        return new Order(orderId, clientData, LocalDateTime.now());
    }
}
