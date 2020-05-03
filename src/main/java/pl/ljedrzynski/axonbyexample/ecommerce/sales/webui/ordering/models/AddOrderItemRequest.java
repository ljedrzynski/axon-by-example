package pl.ljedrzynski.axonbyexample.ecommerce.sales.webui.ordering.models;

import lombok.Data;

@Data
public class AddOrderItemRequest {

    String productId;
    int quantity;
}
