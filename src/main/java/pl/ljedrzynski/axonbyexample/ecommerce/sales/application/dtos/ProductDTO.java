package pl.ljedrzynski.axonbyexample.ecommerce.sales.application.dtos;

import lombok.Value;
import pl.ljedrzynski.axonbyexample.ecommerce.sales.domain.productcatalog.ProductType;
import pl.ljedrzynski.axonbyexample.ecommerce.shared.cannonicalmodel.Money;

@Value
public class ProductDTO {
    String id;
    String name;
    ProductType type;
    Money price;
}
