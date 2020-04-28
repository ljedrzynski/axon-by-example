package pl.ljedrzynski.axonbyexample.ecommerce.sales.domain.productcatalog;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductCatalogService {

    private final ProductRepository productRepository;

    public List<ProductData> getAllProducts() {
        return productRepository.findAll().stream()
                .map(Product::getSnapshot)
                .collect(Collectors.toList());
    }

    public Optional<ProductData> getProductById(String id) {
        return productRepository.findById(id)
                .map(Product::getSnapshot);
    }
}
