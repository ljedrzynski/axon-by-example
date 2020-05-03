package pl.ljedrzynski.axonbyexample.ecommerce.sales.readmodel.order;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OrderReadModelRepository extends MongoRepository<OrderReadModel, String> {

    List<OrderReadModel> findByBuyerIdAndStatus(String buyerId, OrderReadModel.OrderStatus orderStatus);
}
