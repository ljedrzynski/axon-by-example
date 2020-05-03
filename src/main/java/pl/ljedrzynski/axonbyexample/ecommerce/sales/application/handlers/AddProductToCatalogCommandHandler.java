package pl.ljedrzynski.axonbyexample.ecommerce.sales.application.handlers;

import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.springframework.stereotype.Component;
import pl.ljedrzynski.axonbyexample.ecommerce.sales.application.commands.AddProductToCatalogCommand;
import pl.ljedrzynski.axonbyexample.ecommerce.sales.domain.productcatalog.ProductCatalogService;
import pl.ljedrzynski.axonbyexample.ecommerce.sales.domain.productcatalog.ProductData;

@Component
@RequiredArgsConstructor
public class AddProductToCatalogCommandHandler {

    private final ProductCatalogService productCatalogService;

    @CommandHandler
    public void handle(AddProductToCatalogCommand command) {
        var productData = convertToProductData(command);
        productCatalogService.addProductToCatalog(productData);
    }

    private ProductData convertToProductData(AddProductToCatalogCommand command) {
        return new ProductData(command.getProductId(), command.getProductName(), command.getProductType(), command.getProductPrice());
    }
}
