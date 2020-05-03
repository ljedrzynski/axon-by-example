package pl.ljedrzynski.axonbyexample.ecommerce.sales.readmodel.product;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductReadModelRepository extends MongoRepository<ProductReadModel, String> {
}
