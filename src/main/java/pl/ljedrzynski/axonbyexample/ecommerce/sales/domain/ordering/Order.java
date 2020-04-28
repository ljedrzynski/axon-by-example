package pl.ljedrzynski.axonbyexample.ecommerce.sales.domain.ordering;

import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateMember;
import org.axonframework.spring.stereotype.Aggregate;
import pl.ljedrzynski.axonbyexample.ecommerce.sales.application.commands.CreateOrderCommand;
import pl.ljedrzynski.axonbyexample.ecommerce.sales.domain.client.ClientData;
import pl.ljedrzynski.axonbyexample.ecommerce.sales.domain.ordering.events.OrderCreatedEvent;
import pl.ljedrzynski.axonbyexample.ecommerce.sales.domain.ordering.events.OrderItemAddedEvent;
import pl.ljedrzynski.axonbyexample.ecommerce.sales.domain.productcatalog.ProductData;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
@NoArgsConstructor
public class Order {

    public enum OrderStatus {
        OPEN, CLOSED
    }

    @AggregateIdentifier
    private String id;

    private LocalDateTime createDate;

    private OrderStatus status;

    private ClientData clientData;

    @AggregateMember
    private List<OrderItem> orderItems;

    public Order(String id, ClientData clientData, LocalDateTime createDate) {
        apply(new OrderCreatedEvent(id, clientData, createDate));
    }

    public void add(ProductData product, int quantity) {
        apply(new OrderItemAddedEvent(id, product, quantity));
    }

    @EventSourcingHandler
    public void on(OrderItemAddedEvent event) {
        var product = event.getProductData();
        var quantity = event.getQuantity();

        if (contains(product)) {
            increase(product, quantity);
        }

        addNew(product, quantity);
    }

    @EventSourcingHandler
    public void on(OrderCreatedEvent event) {
        id = event.getOrderId();
        createDate = event.getCreateDate();
        clientData = event.getClientData();
        status = OrderStatus.OPEN;
        orderItems = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public ClientData getClientData() {
        return new ClientData(clientData);
    }

    private boolean contains(ProductData product) {
        return this.orderItems.stream()
                .map(OrderItem::getProductData)
                .anyMatch(orderedProduct -> orderedProduct.equals(product));
    }

    private void increase(ProductData product, int quantity) {
        this.orderItems.stream()
                .filter(orderItem -> orderItem.getProductData().equals(product))
                .findFirst()
                .ifPresent(orderItem -> orderItem.changeQuantityBy(quantity));
    }

    private void addNew(ProductData product, int quantity) {
        this.orderItems.add(new OrderItem(UUID.randomUUID().toString(), product, quantity));
    }
}
