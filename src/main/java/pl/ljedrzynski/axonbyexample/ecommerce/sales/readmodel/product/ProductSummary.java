package pl.ljedrzynski.axonbyexample.ecommerce.sales.readmodel.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import pl.ljedrzynski.axonbyexample.ecommerce.sales.domain.productcatalog.ProductType;
import pl.ljedrzynski.axonbyexample.ecommerce.shared.cannonicalmodel.Money;


@Data
@AllArgsConstructor
public class ProductSummary {

    @Id
    private String id;
    private String name;
    private ProductType type;
    private Money price;
}
