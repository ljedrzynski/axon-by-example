package pl.ljedrzynski.axonbyexample.ecommerce.sales.domain.ordering;

import lombok.AllArgsConstructor;
import org.axonframework.modelling.command.EntityId;
import pl.ljedrzynski.axonbyexample.ecommerce.sales.domain.productcatalog.ProductData;

@AllArgsConstructor
public class OrderItem {

    @EntityId
    private final String entityId;

    private int quantity;

    private final ProductData productData;

    public OrderItem(String entityId, ProductData productData, int quantity) {
        this.entityId = entityId;
        this.productData = productData;
        this.quantity = quantity;
    }

    public void changeQuantityBy(int change) {
        int newQuantity = this.quantity + change;
        if (newQuantity <= 0) {
            throw new OrderingOperationException(entityId, "Order item quantity change below 1");
        }
        this.quantity = newQuantity;
    }

    public String getEntityId() {
        return entityId;
    }

    public ProductData getProductData() {
        return this.productData;
    }

    public String getProductId() {
        return this.productData.getId();
    }
}
