package pl.ljedrzynski.axonbyexample.ecommerce.sales.application.commands;

import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Value
public class AddOrderItemCommand {

    @TargetAggregateIdentifier
    String orderId;

    String productId;

    int quantity;

}
