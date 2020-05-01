package pl.ljedrzynski.axonbyexample.ecommerce.sales.domain.ordering.events;

import lombok.Value;

@Value
public class OrderProductQuantityChangedEvent {

    String orderId;
    String productId;
    int quantity;
}
