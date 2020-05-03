package pl.ljedrzynski.axonbyexample.ecommerce;

import lombok.AllArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import pl.ljedrzynski.axonbyexample.ecommerce.sales.application.commands.AddProductToCatalogCommand;
import pl.ljedrzynski.axonbyexample.ecommerce.sales.application.commands.CreateClientCommand;
import pl.ljedrzynski.axonbyexample.ecommerce.sales.domain.productcatalog.ProductType;
import pl.ljedrzynski.axonbyexample.ecommerce.shared.cannonicalmodel.Money;

import java.util.UUID;

@Component
@AllArgsConstructor

public class EventStoreInitializer {

    private final CommandGateway commandGateway;

    @EventListener(ApplicationReadyEvent.class)
    public void dispatchInitialCommands() {
        // Creating test clients
        commandGateway.send(new CreateClientCommand("client-1", "test1"));
        commandGateway.send(new CreateClientCommand("client-2", "test2"));

        // Creating test product catalog
        commandGateway.send(new AddProductToCatalogCommand("product-1", "Wireless Mouse'", ProductType.ELECTRONICS, new Money(29.99)));
        commandGateway.send(new AddProductToCatalogCommand("product-2", "LCD Monitor 24'", ProductType.ELECTRONICS, new Money(599.99)));
        commandGateway.send(new AddProductToCatalogCommand("product-3", "Basic Keyboard", ProductType.ELECTRONICS, new Money(59.99)));
        commandGateway.send(new AddProductToCatalogCommand("product-4", "External SSD 512MB", ProductType.ELECTRONICS, new Money(699.59)));
        commandGateway.send(new AddProductToCatalogCommand("product-5", "PenDrive 8GB", ProductType.ELECTRONICS, new Money(19.89)));
        commandGateway.send(new AddProductToCatalogCommand("product-6", "TV Speakers 5.1", ProductType.ELECTRONICS, new Money(2199.99)));
    }
}
