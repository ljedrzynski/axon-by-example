package pl.ljedrzynski.axonbyexample.ecommerce;

import lombok.AllArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Component;
import pl.ljedrzynski.axonbyexample.ecommerce.sales.application.commands.AddProductToCatalogCommand;
import pl.ljedrzynski.axonbyexample.ecommerce.sales.application.commands.CreateClientCommand;
import pl.ljedrzynski.axonbyexample.ecommerce.sales.domain.productcatalog.ProductType;
import pl.ljedrzynski.axonbyexample.ecommerce.shared.cannonicalmodel.Money;

import javax.annotation.PostConstruct;
import java.util.UUID;

@Component
@AllArgsConstructor
public class EventStoreInitializer {

    private final CommandGateway commandGateway;

    @PostConstruct
    public void initializeTestEventStore() {
        // Creating test clients
        commandGateway.send(new CreateClientCommand(UUID.randomUUID().toString(), "TestClient2"));
        commandGateway.send(new CreateClientCommand(UUID.randomUUID().toString(), "TestClient2"));

        // Creating test product catalog
        commandGateway.send(new AddProductToCatalogCommand(UUID.randomUUID().toString(), "Wireless Mouse'", ProductType.ELECTRONICS, new Money(29.99)));
        commandGateway.send(new AddProductToCatalogCommand(UUID.randomUUID().toString(), "LCD Monitor 24'", ProductType.ELECTRONICS, new Money(599.99)));
        commandGateway.send(new AddProductToCatalogCommand(UUID.randomUUID().toString(), "Basic Keyboard", ProductType.ELECTRONICS, new Money(59.99)));
        commandGateway.send(new AddProductToCatalogCommand(UUID.randomUUID().toString(), "External SSD 512MB", ProductType.ELECTRONICS, new Money(699.59)));
        commandGateway.send(new AddProductToCatalogCommand(UUID.randomUUID().toString(), "PenDrive 8GB", ProductType.ELECTRONICS, new Money(19.89)));
        commandGateway.send(new AddProductToCatalogCommand(UUID.randomUUID().toString(), "TV Speakers 5.1", ProductType.ELECTRONICS, new Money(2199.99)));
    }
}
