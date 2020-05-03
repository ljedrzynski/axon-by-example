package pl.ljedrzynski.axonbyexample.ecommerce.sales.domain.ordering;

import lombok.NoArgsConstructor;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateMember;
import org.axonframework.spring.stereotype.Aggregate;
import pl.ljedrzynski.axonbyexample.ecommerce.sales.domain.client.ClientData;
import pl.ljedrzynski.axonbyexample.ecommerce.sales.domain.ordering.events.OrderCreatedEvent;
import pl.ljedrzynski.axonbyexample.ecommerce.sales.domain.ordering.events.ProductAddedToOrderEvent;
import pl.ljedrzynski.axonbyexample.ecommerce.sales.domain.ordering.events.OrderProductQuantityChangedEvent;
import pl.ljedrzynski.axonbyexample.ecommerce.sales.domain.ordering.events.ProductRemovedFromOrderEvent;
import pl.ljedrzynski.axonbyexample.ecommerce.sales.domain.productcatalog.ProductData;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
@NoArgsConstructor
public class Order {

    public enum OrderStatus {
        OPEN, CLOSED, CANCELLED
    }

    @AggregateIdentifier
    private String id;

    private LocalDateTime createDate;

    private OrderStatus status;

    private ClientData clientData;

    @AggregateMember
    private List<OrderItem> items;


    public Order(String id, ClientData clientData, LocalDateTime createDate) {
        apply(new OrderCreatedEvent(id, clientData, createDate, Order.OrderStatus.OPEN));
    }

    public void addItem(ProductData product, int quantity) {
        checkIfQuantityNumberIsValid(quantity);
        apply(new ProductAddedToOrderEvent(UUID.randomUUID().toString(), id, product, quantity));
    }

    public void changeItemQuantity(String productId, int quantity) {
        checkIfContainsProduct(productId);
        apply(new OrderProductQuantityChangedEvent(id, productId, quantity));
    }

    public void removeItem(String productId) {
        checkIfContainsProduct(productId);
        apply(new ProductRemovedFromOrderEvent(id, productId));
    }

    @EventSourcingHandler
    public void on(OrderCreatedEvent event) {
        id = event.getOrderId();
        createDate = event.getCreateDate();
        clientData = event.getClientData();
        status = event.getOrderStatus();
        items = new ArrayList<>();
    }

    @EventSourcingHandler
    public void on(ProductAddedToOrderEvent event) {
        var orderItemId = event.getOrderItemId();
        var productData = event.getProductData();
        var quantity = event.getQuantity();

        if (containsItemByProductId(productData.getId())) {
            changeItemQuantityBy(productData.getId(), quantity);
        }

        addNewItem(orderItemId, productData, quantity);
    }

    @EventSourcingHandler
    public void on(OrderProductQuantityChangedEvent event) {
        changeItemQuantityTo(event.getProductId(), event.getQuantity());
    }

    @EventSourcingHandler
    public void on(ProductRemovedFromOrderEvent event) {
        this.items.removeIf(orderItem -> orderItem.getProductId().equals(event.getProductId()));
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

    private boolean containsItemByProductId(String productId) {
        return getOrderItem(productId)
                .isPresent();
    }

    private void changeItemQuantityBy(String productId, int quantity) {
        getOrderItem(productId)
                .ifPresent(orderItem -> orderItem.changeQuantityBy(quantity));
    }

    private void changeItemQuantityTo(String productId, int quantity) {
        getOrderItem(productId)
                .ifPresent(orderItem -> orderItem.changeQuantity(quantity));
    }

    private void addNewItem(String itemId, ProductData product, int quantity) {
        this.items.add(new OrderItem(itemId, product, quantity));
    }

    private void checkIfQuantityNumberIsValid(int quantity) {
        if (quantity <= 0) {
            throw new OrderingOperationException(id, "Cannot add product with quantity less than 1");
        }
    }

    private void checkIfContainsProduct(String productId) {
        this.items.stream()
                .map(OrderItem::getProductId)
                .filter(existProductId -> existProductId.equals(productId))
                .findAny()
                .orElseThrow(() -> new OrderingOperationException(id, "Order does not contain product with id %s", productId));
    }

    private Optional<OrderItem> getOrderItem(String productId) {
        return this.items.stream()
                .filter(orderItem -> orderItem.getProductId().equals(productId))
                .findFirst();
    }
}
