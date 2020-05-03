package pl.ljedrzynski.axonbyexample.ecommerce.sales.application.queries;

import lombok.Value;

@Value
public class GetOrderQuery {

    String orderId;
}
