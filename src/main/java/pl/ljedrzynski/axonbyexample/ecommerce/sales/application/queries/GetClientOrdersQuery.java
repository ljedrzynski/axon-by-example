package pl.ljedrzynski.axonbyexample.ecommerce.sales.application.queries;

import lombok.Value;
import pl.ljedrzynski.axonbyexample.ecommerce.sales.readmodel.order.OrderReadModel;

@Value
public class GetClientOrdersQuery {

    String clientId;
    OrderReadModel.OrderStatus orderStatus;
}
