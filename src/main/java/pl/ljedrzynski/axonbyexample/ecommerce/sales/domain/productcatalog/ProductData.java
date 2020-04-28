package pl.ljedrzynski.axonbyexample.ecommerce.sales.domain.productcatalog;

import lombok.Value;
import pl.ljedrzynski.axonbyexample.ecommerce.shared.cannonicalmodel.Money;

@Value
public class ProductData {

    String id;

    String name;

    ProductType type;

    Money price;

}
