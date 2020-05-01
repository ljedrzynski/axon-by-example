package pl.ljedrzynski.axonbyexample.ecommerce.sales.application.handlers;

import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.springframework.stereotype.Component;
import pl.ljedrzynski.axonbyexample.ecommerce.sales.application.commands.AddProductToOrderCommand;
import pl.ljedrzynski.axonbyexample.ecommerce.sales.domain.ordering.OrderingService;

@Component
@RequiredArgsConstructor
class AddProductToOrderCommandHandler {

    private final OrderingService orderingService;

    @CommandHandler
    void handle(AddProductToOrderCommand command) {
        orderingService.addProductToOrder(command.getOrderId(), command.getProductId(), command.getQuantity());
    }
}
