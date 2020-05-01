package pl.ljedrzynski.axonbyexample.ecommerce.sales.domain.ordering.events;

import lombok.Value;
import pl.ljedrzynski.axonbyexample.ecommerce.sales.domain.productcatalog.ProductData;

@Value
public class ProductAddedToOrderEvent {

    String orderId;
    ProductData productData;
    int quantity;
}
