package pl.ljedrzynski.axonbyexample.ecommerce.sales.domain.ordering;

import lombok.RequiredArgsConstructor;
import org.axonframework.modelling.command.Repository;
import org.springframework.stereotype.Service;
import pl.ljedrzynski.axonbyexample.ecommerce.sales.domain.client.ClientData;
import pl.ljedrzynski.axonbyexample.ecommerce.sales.domain.client.ClientService;
import pl.ljedrzynski.axonbyexample.ecommerce.sales.domain.productcatalog.ProductCatalogService;
import pl.ljedrzynski.axonbyexample.ecommerce.system.context.SystemContext;

import java.util.function.Consumer;

@Service
@RequiredArgsConstructor
public class OrderingService {

    private final Repository<Order> orderRepository;
    private final OrderFactory orderFactory;
    private final ProductCatalogService productCatalogService;
    private final ClientService clientService;
    private final SystemContext systemContext;

    public void createNewOrder(String orderId) throws Exception {
        var clientData = getLoggedClientData();
        orderRepository.newInstance(() -> orderFactory.create(orderId, clientData));

    }

    public void addProductToOrder(String orderId, String productId, int quantity) {
        Consumer<Order> addProductToOrder = order -> {
            var product = productCatalogService.getProductDataById(productId);
            order.addItem(product, quantity);
        };

        executeOnAggregate(orderId, addProductToOrder);
    }

    public void changeOrderedProductQuantity(String orderId, String productId, int quantity) {
        executeOnAggregate(orderId, order -> order.changeItemQuantity(productId, quantity));
    }

    public void removeProductFromOrder(String orderId, String productId) {
        executeOnAggregate(orderId, order -> order.removeItem(productId));
    }

    private void executeOnAggregate(String orderId, Consumer<Order> orderConsumer) {
        orderRepository.load(orderId).execute(orderConsumer);
    }

    private ClientData getLoggedClientData() {
        var loggedUserId = systemContext.getLoggedUser().getId();
        return clientService.getClientData(loggedUserId);
    }
}
