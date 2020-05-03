package pl.ljedrzynski.axonbyexample.ecommerce.sales.readmodel.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import pl.ljedrzynski.axonbyexample.ecommerce.sales.domain.productcatalog.ProductType;

@Document
@Data
@AllArgsConstructor
public class OrderItemReadModel {

    @Id
    private final String id;
    private final String productId;
    private final String productName;
    private ProductType productType;
    private String productPrice;
    int productQuantity;
}
