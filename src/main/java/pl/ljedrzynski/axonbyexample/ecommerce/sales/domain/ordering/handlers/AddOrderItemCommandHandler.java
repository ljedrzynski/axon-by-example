package pl.ljedrzynski.axonbyexample.ecommerce.sales.domain.ordering.handlers;

import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.modelling.command.Repository;
import org.springframework.stereotype.Component;
import pl.ljedrzynski.axonbyexample.ecommerce.sales.application.commands.AddOrderItemCommand;
import pl.ljedrzynski.axonbyexample.ecommerce.sales.domain.ordering.Order;
import pl.ljedrzynski.axonbyexample.ecommerce.sales.domain.ordering.OrderingDomainException;
import pl.ljedrzynski.axonbyexample.ecommerce.sales.domain.productcatalog.ProductCatalogService;

@Component
@RequiredArgsConstructor
class AddOrderItemCommandHandler {

    private final ProductCatalogService productCatalogService;
    private final Repository<Order> orderRepository;

    @CommandHandler
    void handle(AddOrderItemCommand cmd) {

        var productData = productCatalogService.getProductById(cmd.getProductId())
                .orElseThrow(() -> new OrderingDomainException(cmd.getProductId(), "Cannot find product with provided id"));
        var orderAggregate = orderRepository.load(cmd.getOrderId());

        orderAggregate.execute(order -> order.add(productData, cmd.getQuantity()));
    }

}
