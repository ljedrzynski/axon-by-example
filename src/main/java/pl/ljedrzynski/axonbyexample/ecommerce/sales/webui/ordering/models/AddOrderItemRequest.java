package pl.ljedrzynski.axonbyexample.ecommerce.sales.webui.ordering.models;

import lombok.Value;

@Value
public class AddOrderItemRequest {

    String productId;
    int quantity;
}
