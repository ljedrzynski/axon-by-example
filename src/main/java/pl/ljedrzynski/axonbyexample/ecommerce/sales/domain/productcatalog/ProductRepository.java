package pl.ljedrzynski.axonbyexample.ecommerce.sales.domain.productcatalog;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String> {
}
