package pl.ljedrzynski.axonbyexample.ecommerce.sales.domain.productcatalog.events;

import lombok.Value;
import pl.ljedrzynski.axonbyexample.ecommerce.sales.domain.productcatalog.ProductType;
import pl.ljedrzynski.axonbyexample.ecommerce.shared.cannonicalmodel.Money;

@Value
public class ProductAddedToCatalogEvent {

    String id;
    String name;
    ProductType type;
    Money price;
}
