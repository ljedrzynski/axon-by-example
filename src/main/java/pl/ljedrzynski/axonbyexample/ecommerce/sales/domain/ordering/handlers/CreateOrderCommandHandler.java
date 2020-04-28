package pl.ljedrzynski.axonbyexample.ecommerce.sales.domain.ordering.handlers;

import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.modelling.command.Aggregate;
import org.axonframework.modelling.command.Repository;
import org.springframework.stereotype.Component;
import pl.ljedrzynski.axonbyexample.ecommerce.sales.application.commands.CreateOrderCommand;
import pl.ljedrzynski.axonbyexample.ecommerce.sales.domain.client.ClientService;
import pl.ljedrzynski.axonbyexample.ecommerce.sales.domain.ordering.Order;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
class CreateOrderCommandHandler {

    private final ClientService clientService;
    private final Repository<Order> orderRepository;

    @CommandHandler
    void handle(CreateOrderCommand cmd) throws Exception {

        var clientData = clientService.getClientById(cmd.getClientId());

        orderRepository.loadOrCreate(cmd.getOrderId(), () -> new Order(cmd.getOrderId(), clientData, LocalDateTime.now()));
    }
}
