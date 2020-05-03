package pl.ljedrzynski.axonbyexample.ecommerce.sales.webui.ordering;

import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.*;
import pl.ljedrzynski.axonbyexample.ecommerce.sales.application.commands.AddProductToOrderCommand;
import pl.ljedrzynski.axonbyexample.ecommerce.sales.application.commands.ChangeOrderedProductQuantityCommand;
import pl.ljedrzynski.axonbyexample.ecommerce.sales.application.commands.CreateOrderCommand;
import pl.ljedrzynski.axonbyexample.ecommerce.sales.application.commands.RemoveProductFromOrderCommand;
import pl.ljedrzynski.axonbyexample.ecommerce.sales.application.queries.GetOrderQuery;
import pl.ljedrzynski.axonbyexample.ecommerce.sales.readmodel.order.OrderReadModel;
import pl.ljedrzynski.axonbyexample.ecommerce.sales.webui.ordering.models.AddOrderItemRequest;
import pl.ljedrzynski.axonbyexample.ecommerce.sales.webui.ordering.models.ChangeOrderItemQuantityRequest;

import java.util.UUID;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderingController {

    private final CommandGateway commandGateway;
    private final QueryGateway queryGateway;

    @PostMapping
    public String createOrder() {
        String aggregateId = UUID.randomUUID().toString();
        commandGateway.sendAndWait(new CreateOrderCommand(aggregateId));
        return aggregateId;
    }

    @PostMapping("/{orderId}/order-items")
    public void addOrderItem(@PathVariable String orderId, @RequestBody AddOrderItemRequest request) {
        commandGateway.sendAndWait(new AddProductToOrderCommand(orderId, request.getProductId(), request.getQuantity()));
    }

    @PutMapping("/{orderId}/order-items/{productId}")
    public void changeOrderItemQuantity(@PathVariable String orderId, @PathVariable String productId, @RequestBody ChangeOrderItemQuantityRequest request) {
        commandGateway.sendAndWait(new ChangeOrderedProductQuantityCommand(orderId, productId, request.getQuantity()));
    }

    @DeleteMapping("/{orderId}/order-items/{productId}")
    public void removeOrderItem(@PathVariable String orderId, @PathVariable String productId) {
        commandGateway.sendAndWait(new RemoveProductFromOrderCommand(orderId, productId));
    }

    @GetMapping("/{orderId}")
    public OrderReadModel getOrder(@PathVariable String orderId) {
        return queryGateway.query(new GetOrderQuery(orderId), ResponseTypes.instanceOf(OrderReadModel.class)).join();
    }
}
