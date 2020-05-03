package pl.ljedrzynski.axonbyexample.ecommerce.sales.readmodel.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import pl.ljedrzynski.axonbyexample.ecommerce.sales.domain.productcatalog.ProductType;
import pl.ljedrzynski.axonbyexample.ecommerce.shared.cannonicalmodel.Money;


@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductReadModel {

    @Id
    private String id;
    private String name;
    private ProductType type;
    private String price;
}
