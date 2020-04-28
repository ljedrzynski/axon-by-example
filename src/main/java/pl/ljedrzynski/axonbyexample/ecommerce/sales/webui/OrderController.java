package pl.ljedrzynski.axonbyexample.ecommerce.sales.webui;

import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.ljedrzynski.axonbyexample.ecommerce.sales.application.commands.CreateClientCommand;
import pl.ljedrzynski.axonbyexample.ecommerce.sales.application.commands.CreateOrderCommand;

import java.util.UUID;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final CommandGateway commandGateway;

    @PostMapping
    public String createOrder() {
        String orderId = UUID.randomUUID().toString();
        String clientId = UUID.randomUUID().toString();
        commandGateway.sendAndWait(new CreateClientCommand(clientId, "Testowy"));
        commandGateway.send(new CreateOrderCommand(orderId, clientId));
        return orderId;
    }
}
