package pl.ljedrzynski.axonbyexample.ecommerce.sales.readmodel.product;

import lombok.RequiredArgsConstructor;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;
import pl.ljedrzynski.axonbyexample.ecommerce.sales.application.queries.GetAllProductsQuery;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductQueryHandler {

    private final ProductReadModelRepository productSummary;

    @QueryHandler
    public List<ProductReadModel> handle(GetAllProductsQuery query) {
        return productSummary.findAll();
    }
}
