package pl.ljedrzynski.axonbyexample.ecommerce.sales.webui;

import lombok.RequiredArgsConstructor;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.ljedrzynski.axonbyexample.ecommerce.sales.application.queries.GetClientDetailsQuery;
import pl.ljedrzynski.axonbyexample.ecommerce.sales.application.queries.GetClientOrdersQuery;
import pl.ljedrzynski.axonbyexample.ecommerce.sales.readmodel.client.ClientReadModel;
import pl.ljedrzynski.axonbyexample.ecommerce.sales.readmodel.order.OrderReadModel;

import java.util.List;

@RequestMapping("api/clients")
@RestController
@RequiredArgsConstructor
public class ClientController {

    private final QueryGateway queryGateway;

    @GetMapping("/{clientId}")
    public ClientReadModel getClientDetails(@PathVariable String clientId) {
        return queryGateway.query(new GetClientDetailsQuery(clientId),
                ResponseTypes.instanceOf(ClientReadModel.class))
                .join();
    }

    @GetMapping("/{clientId}/orders")
    public List<OrderReadModel> getClientOrders(@PathVariable String clientId) {
        return queryGateway.query(new GetClientOrdersQuery(clientId, OrderReadModel.OrderStatus.OPEN),
                ResponseTypes.multipleInstancesOf(OrderReadModel.class))
                .join();
    }
}
