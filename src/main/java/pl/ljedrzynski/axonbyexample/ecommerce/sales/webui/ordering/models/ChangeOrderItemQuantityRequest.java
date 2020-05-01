package pl.ljedrzynski.axonbyexample.ecommerce.sales.webui.ordering.models;

import lombok.Value;

@Value
public class ChangeOrderItemQuantityRequest {

    int quantity;
}
