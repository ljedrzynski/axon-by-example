package pl.ljedrzynski.axonbyexample.ecommerce.sales.application.handlers;

import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.springframework.stereotype.Component;
import pl.ljedrzynski.axonbyexample.ecommerce.sales.application.commands.CreateOrderCommand;
import pl.ljedrzynski.axonbyexample.ecommerce.sales.domain.ordering.OrderingService;

@Component
@RequiredArgsConstructor
class CreateOrderCommandHandler {

    private final OrderingService orderingService;

    @CommandHandler
    void handle(CreateOrderCommand command) throws Exception {
        orderingService.createNewOrder(command.getOrderId());
    }
}
