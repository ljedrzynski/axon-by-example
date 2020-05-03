package pl.ljedrzynski.axonbyexample.ecommerce.sales.domain.ordering;

import lombok.AllArgsConstructor;
import lombok.Value;
import pl.ljedrzynski.axonbyexample.ecommerce.sales.domain.productcatalog.ProductData;

@Value
@AllArgsConstructor
public class OrderItemData {

    String id;
    ProductData productData;
    int quantity;
}
