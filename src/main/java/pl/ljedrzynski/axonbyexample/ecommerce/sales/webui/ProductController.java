package pl.ljedrzynski.axonbyexample.ecommerce.sales.webui;

import lombok.RequiredArgsConstructor;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.ljedrzynski.axonbyexample.ecommerce.sales.application.queries.GetAllProductsQuery;
import pl.ljedrzynski.axonbyexample.ecommerce.sales.domain.productcatalog.ProductData;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final QueryGateway queryGateway;

    @GetMapping
    public List<ProductData> getProducts() {
        return queryGateway.query(
                new GetAllProductsQuery(),
                ResponseTypes.multipleInstancesOf(ProductData.class))
                .join();
    }
}
