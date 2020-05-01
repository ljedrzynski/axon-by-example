package pl.ljedrzynski.axonbyexample.ecommerce.sales.domain.productcatalog;

import org.springframework.stereotype.Component;

@Component
public class ProductFactory {

    public Product create(ProductData productData) {
        return new Product(productData.getId(), productData.getName(), productData.getType(), productData.getPrice());
    }
}
