package pl.ljedrzynski.axonbyexample.ecommerce.sales.readmodel.order;

import lombok.RequiredArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;
import pl.ljedrzynski.axonbyexample.ecommerce.sales.domain.ordering.events.OrderCreatedEvent;
import pl.ljedrzynski.axonbyexample.ecommerce.sales.domain.ordering.events.OrderProductQuantityChangedEvent;
import pl.ljedrzynski.axonbyexample.ecommerce.sales.domain.ordering.events.ProductAddedToOrderEvent;
import pl.ljedrzynski.axonbyexample.ecommerce.sales.domain.ordering.events.ProductRemovedFromOrderEvent;

import java.time.format.DateTimeFormatter;
import java.util.function.Consumer;

@Component
@RequiredArgsConstructor
public class OrderProjectionHandler {

    private final OrderReadModelRepository orderReadModelRepository;

    @EventHandler
    private void handle(OrderCreatedEvent event) {

        var orderReadModel = new OrderReadModel(event.getOrderId(),
                event.getCreateDate().format(DateTimeFormatter.ISO_DATE_TIME),
                OrderReadModel.OrderStatus.from(event.getOrderStatus()),
                event.getClientData().getId(),
                event.getClientData().getName());

        orderReadModelRepository.save(orderReadModel);
    }

    @EventHandler
    private void handle(ProductAddedToOrderEvent event) {

        Consumer<OrderReadModel> addOrderItem = orderReadModel -> {
            var productData = event.getProductData();

            var orderItemReadModel = new OrderItemReadModel(
                    event.getOrderItemId(),
                    productData.getId(),
                    productData.getName(),
                    productData.getType(),
                    productData.getPrice().toString(),
                    event.getQuantity());

            var orderItems = orderReadModel.getItems();
            orderItems.add(orderItemReadModel);

            orderReadModel.setItemsCount(orderItems.size());
        };

        projectOrder(event.getOrderId(), addOrderItem);
    }

    @EventHandler
    private void handle(ProductRemovedFromOrderEvent event) {

        Consumer<OrderReadModel> removeOrderItem = orderReadModel -> {
            orderReadModel.getItems().removeIf(orderItem -> orderItem.getProductId().equals(event.getProductId()));
            orderReadModel.setItemsCount(orderReadModel.getItems().size());
        };

        projectOrder(event.getOrderId(), removeOrderItem);
    }

    @EventHandler
    private void handle(OrderProductQuantityChangedEvent event) {

        Consumer<OrderReadModel> changeProductQuantity = orderReadModel ->
                orderReadModel.getItems().stream()
                        .filter(orderItem -> orderItem.getProductId().equals(event.getProductId()))
                        .findFirst()
                        .ifPresent(orderItem -> orderItem.setProductQuantity(event.getQuantity()));

        projectOrder(event.getOrderId(), changeProductQuantity);
    }

    private void projectOrder(String orderId, Consumer<OrderReadModel> consumer) {

        var orderReadModel = orderReadModelRepository.findById(orderId)
                .orElseThrow(() -> new OrderProjectionException("Cannot find order in read model with id: %s§", orderId));

        consumer.accept(orderReadModel);

        orderReadModelRepository.save(orderReadModel);
    }
}
