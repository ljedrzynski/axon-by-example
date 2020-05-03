package pl.ljedrzynski.axonbyexample.ecommerce.sales.domain.ordering.events;

import lombok.Value;
import pl.ljedrzynski.axonbyexample.ecommerce.sales.domain.client.ClientData;
import pl.ljedrzynski.axonbyexample.ecommerce.sales.domain.ordering.Order;

import java.time.LocalDateTime;

@Value
public class OrderCreatedEvent {

    String orderId;
    ClientData clientData;
    LocalDateTime createDate;
    Order.OrderStatus orderStatus;
}
