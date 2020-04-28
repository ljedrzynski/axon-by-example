package pl.ljedrzynski.axonbyexample.ecommerce.sales.readmodel;

import lombok.RequiredArgsConstructor;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;
import pl.ljedrzynski.axonbyexample.ecommerce.sales.application.dtos.ProductDTO;
import pl.ljedrzynski.axonbyexample.ecommerce.sales.application.queries.GetAllProductsQuery;
import pl.ljedrzynski.axonbyexample.ecommerce.sales.domain.productcatalog.ProductCatalogService;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ProductQueryHandler {

    private final ProductCatalogService productCatalogService;

    @QueryHandler
    public List<ProductDTO> handle(GetAllProductsQuery query) {
        return productCatalogService.getAllProducts().stream()
                .map(productData -> new ProductDTO(productData.getId(), productData.getName(), productData.getType(), productData.getPrice()))
                .collect(Collectors.toList());
    }
}
