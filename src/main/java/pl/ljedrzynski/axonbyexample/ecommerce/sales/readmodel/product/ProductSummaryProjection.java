package pl.ljedrzynski.axonbyexample.ecommerce.sales.readmodel.product;

import lombok.AllArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;
import pl.ljedrzynski.axonbyexample.ecommerce.sales.domain.productcatalog.events.ProductAddedToCatalogEvent;

@Component
@AllArgsConstructor
public class ProductSummaryProjection {

    private final ProductSummaryRepository productSummaryRepository;

    @EventHandler
    public void handle(ProductAddedToCatalogEvent event) {
        var productSummary = new ProductSummary(event.getId(), event.getName(), event.getType(), event.getPrice());
        productSummaryRepository.save(productSummary);
    }
}
