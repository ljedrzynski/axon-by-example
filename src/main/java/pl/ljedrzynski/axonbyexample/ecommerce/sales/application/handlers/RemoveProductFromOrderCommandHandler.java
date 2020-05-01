package pl.ljedrzynski.axonbyexample.ecommerce.sales.application.handlers;

import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.springframework.stereotype.Component;
import pl.ljedrzynski.axonbyexample.ecommerce.sales.application.commands.RemoveProductFromOrderCommand;
import pl.ljedrzynski.axonbyexample.ecommerce.sales.domain.ordering.OrderingService;

@Component
@RequiredArgsConstructor
class RemoveProductFromOrderCommandHandler {

    private final OrderingService orderingService;

    @CommandHandler
    void handle(RemoveProductFromOrderCommand command) {
        orderingService.removeProductFromOrder(command.getOrderId(), command.getProductId());
    }
}
