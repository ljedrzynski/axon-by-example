package pl.ljedrzynski.axonbyexample.ecommerce.sales.domain.productcatalog;

import lombok.NoArgsConstructor;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;
import pl.ljedrzynski.axonbyexample.ecommerce.sales.domain.productcatalog.events.ProductAddedToCatalogEvent;
import pl.ljedrzynski.axonbyexample.ecommerce.shared.cannonicalmodel.Money;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
@NoArgsConstructor
public class Product {

    @AggregateIdentifier
    private String id;

    private String name;

    private ProductType type;

    private Money price;


    public Product(String id, String name, ProductType type, Money price) {
        apply(new ProductAddedToCatalogEvent(id, name, type, price));
    }

    @EventSourcingHandler
    public void handle(ProductAddedToCatalogEvent event) {
        this.id = event.getId();
        this.name = event.getName();
        this.price = event.getPrice();
        this.type = event.getType();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ProductData getSnapshot() {
        return new ProductData(id, name, type, price);
    }
}
