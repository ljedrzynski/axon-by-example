package pl.ljedrzynski.axonbyexample.ecommerce.sales.application.commands;

import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;
import pl.ljedrzynski.axonbyexample.ecommerce.sales.domain.productcatalog.ProductType;
import pl.ljedrzynski.axonbyexample.ecommerce.shared.cannonicalmodel.Money;

@Value
public class AddProductToCatalogCommand {

    @TargetAggregateIdentifier
    String productId;
    String productName;
    ProductType productType;
    Money productPrice;
}
