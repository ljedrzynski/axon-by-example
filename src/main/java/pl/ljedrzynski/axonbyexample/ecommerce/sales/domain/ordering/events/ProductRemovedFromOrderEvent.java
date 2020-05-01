package pl.ljedrzynski.axonbyexample.ecommerce.sales.domain.ordering.events;

import lombok.Value;

@Value
public class ProductRemovedFromOrderEvent {

    String orderId;
    String productId;
}
