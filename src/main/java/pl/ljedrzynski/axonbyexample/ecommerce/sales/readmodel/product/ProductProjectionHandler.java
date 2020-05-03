package pl.ljedrzynski.axonbyexample.ecommerce.sales.readmodel.product;

import lombok.AllArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;
import pl.ljedrzynski.axonbyexample.ecommerce.sales.domain.productcatalog.events.ProductAddedToCatalogEvent;

@Component
@AllArgsConstructor
public class ProductProjectionHandler {

    private final ProductReadModelRepository productReadModelRepository;

    @EventHandler
    public void handle(ProductAddedToCatalogEvent event) {
        var productReadModel = new ProductReadModel(event.getId(), event.getName(), event.getType(), event.getPrice().toString());
        productReadModelRepository.save(productReadModel);
    }
}
