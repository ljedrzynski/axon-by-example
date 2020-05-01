package pl.ljedrzynski.axonbyexample.ecommerce.sales.readmodel.order;

import org.springframework.data.annotation.Id;
import pl.ljedrzynski.axonbyexample.ecommerce.sales.domain.client.ClientData;
import pl.ljedrzynski.axonbyexample.ecommerce.sales.domain.ordering.Order;
import pl.ljedrzynski.axonbyexample.ecommerce.sales.domain.ordering.OrderItem;

import java.time.LocalDateTime;
import java.util.List;

public class OrderSummary {

    @Id
    private String id;

    private LocalDateTime createDate;

    private Order.OrderStatus status;

    private ClientData clientData;

    private List<OrderItem> orderItems;
}
