package pl.ljedrzynski.axonbyexample.ecommerce.sales.readmodel.order;

import lombok.RequiredArgsConstructor;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;
import pl.ljedrzynski.axonbyexample.ecommerce.sales.application.queries.GetClientOrdersQuery;
import pl.ljedrzynski.axonbyexample.ecommerce.sales.application.queries.GetOrderQuery;

import java.util.List;

@Component
@RequiredArgsConstructor
public class OrderQueryHandler {

    private final OrderReadModelRepository orderReadModelRepository;

    @QueryHandler
    public OrderReadModel handle(GetOrderQuery query) {
        return orderReadModelRepository.findById(query.getOrderId())
                .orElse(null);
    }

    @QueryHandler
    public List<OrderReadModel> handle(GetClientOrdersQuery query) {
        return orderReadModelRepository.findByBuyerIdAndStatus(query.getClientId(), query.getOrderStatus());
    }
}
