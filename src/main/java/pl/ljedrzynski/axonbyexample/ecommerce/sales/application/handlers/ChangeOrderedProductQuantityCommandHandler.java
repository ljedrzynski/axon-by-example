package pl.ljedrzynski.axonbyexample.ecommerce.sales.application.handlers;

import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.springframework.stereotype.Component;
import pl.ljedrzynski.axonbyexample.ecommerce.sales.application.commands.ChangeOrderedProductQuantityCommand;
import pl.ljedrzynski.axonbyexample.ecommerce.sales.domain.ordering.OrderingService;

@Component
@RequiredArgsConstructor
public class ChangeOrderedProductQuantityCommandHandler {

    private final OrderingService orderingService;

    @CommandHandler
    void handle(ChangeOrderedProductQuantityCommand command) {
        orderingService.changeOrderedProductQuantity(command.getOrderId(), command.getProductId(), command.getProductQuantity());
    }
}
