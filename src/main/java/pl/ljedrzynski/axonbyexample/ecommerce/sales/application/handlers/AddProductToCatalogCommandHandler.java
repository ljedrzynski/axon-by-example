package pl.ljedrzynski.axonbyexample.ecommerce.sales.application.handlers;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.axonframework.commandhandling.CommandHandler;
import pl.ljedrzynski.axonbyexample.ecommerce.sales.application.commands.AddProductToCatalogCommand;
import pl.ljedrzynski.axonbyexample.ecommerce.sales.domain.productcatalog.ProductCatalogService;
import pl.ljedrzynski.axonbyexample.ecommerce.sales.domain.productcatalog.ProductData;

@RequiredArgsConstructor
public class AddProductToCatalogCommandHandler {

    private final ProductCatalogService productCatalogService;

    @SneakyThrows
    @CommandHandler
    public void handle(AddProductToCatalogCommand command) {
        var productData = convertToProductData(command);
        productCatalogService.addProductToCatalog(productData);
    }

    private ProductData convertToProductData(AddProductToCatalogCommand command) {
        return new ProductData(command.getId(), command.getName(), command.getType(), command.getPrice());
    }
}
