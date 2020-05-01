package pl.ljedrzynski.axonbyexample.ecommerce.sales.readmodel.order;

import lombok.Data;
import org.springframework.data.annotation.Id;
import pl.ljedrzynski.axonbyexample.ecommerce.sales.domain.productcatalog.ProductData;

@Data
public class OrderItemSummary {

    @Id
    private String entityId;

    private int quantity;

    private ProductData productData;
}
