package pl.ljedrzynski.axonbyexample.ecommerce.sales.readmodel.product;

import lombok.RequiredArgsConstructor;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;
import pl.ljedrzynski.axonbyexample.ecommerce.sales.application.queries.GetAllProductsQuery;
import pl.ljedrzynski.axonbyexample.ecommerce.sales.domain.productcatalog.ProductData;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ProductQueryHandler {

    private final ProductSummaryRepository productSummary;

    @QueryHandler
    public List<ProductData> handle(GetAllProductsQuery query) {
        return productSummary.findAll().stream()
                .map(productSummary -> new ProductData(productSummary.getId(), productSummary.getName(), productSummary.getType(), productSummary.getPrice()))
                .collect(Collectors.toList());
    }
}
