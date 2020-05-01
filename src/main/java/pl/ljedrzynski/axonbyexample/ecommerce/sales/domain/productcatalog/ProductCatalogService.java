package pl.ljedrzynski.axonbyexample.ecommerce.sales.domain.productcatalog;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.axonframework.modelling.command.Repository;
import org.springframework.stereotype.Service;
import pl.ljedrzynski.axonbyexample.ecommerce.system.context.SystemContext;
import pl.ljedrzynski.axonbyexample.ecommerce.system.context.UserContext;

@Service
@RequiredArgsConstructor
public class ProductCatalogService {

    private final ProductFactory productFactory;
    private final Repository<Product> productRepository;
    private final SystemContext systemContext;

    @SneakyThrows
    public void addProductToCatalog(ProductData productData) {
        checkIfAuthorized(systemContext.getLoggedUser());
        productRepository.newInstance(() -> productFactory.create(productData));
    }

    public ProductData getProductDataById(String id) {
        return productRepository.load(id)
                .invoke(Product::getSnapshot);
    }

    private void checkIfAuthorized(UserContext loggedUser) {
        // Check if user is authorized to managing the product catalog
    }
}
