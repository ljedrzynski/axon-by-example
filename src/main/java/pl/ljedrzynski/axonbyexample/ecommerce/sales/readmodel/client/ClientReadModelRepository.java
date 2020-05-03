package pl.ljedrzynski.axonbyexample.ecommerce.sales.readmodel.client;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientReadModelRepository extends MongoRepository<ClientReadModel, String> {
}
