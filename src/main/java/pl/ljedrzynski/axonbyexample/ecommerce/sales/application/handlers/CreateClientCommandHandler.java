package pl.ljedrzynski.axonbyexample.ecommerce.sales.application.handlers;

import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.springframework.stereotype.Component;
import pl.ljedrzynski.axonbyexample.ecommerce.sales.application.commands.CreateClientCommand;
import pl.ljedrzynski.axonbyexample.ecommerce.sales.domain.client.ClientData;
import pl.ljedrzynski.axonbyexample.ecommerce.sales.domain.client.ClientService;

@Component
@RequiredArgsConstructor
public class CreateClientCommandHandler {

    private final ClientService clientService;

    @CommandHandler
    public void handle(CreateClientCommand command) {
        var clientData = convertToClientData(command);
        clientService.createNewClient(clientData);
    }

    private ClientData convertToClientData(CreateClientCommand command) {
        return new ClientData(command.getClientId(), command.getClientName());
    }
}
