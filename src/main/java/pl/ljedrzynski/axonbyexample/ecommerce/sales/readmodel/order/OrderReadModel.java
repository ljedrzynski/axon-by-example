package pl.ljedrzynski.axonbyexample.ecommerce.sales.readmodel.order;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import pl.ljedrzynski.axonbyexample.ecommerce.sales.domain.ordering.Order;

import java.util.ArrayList;
import java.util.List;

@Document
@NoArgsConstructor
@Data
public class OrderReadModel {

    public enum OrderStatus {
        OPEN,
        CLOSE;

        public static OrderStatus from(Order.OrderStatus domainOrderStatus) {
            if (domainOrderStatus == Order.OrderStatus.OPEN) {
                return OPEN;
            }
            if (domainOrderStatus == Order.OrderStatus.CLOSED) {
                return CLOSE;
            }
            return null;
        }
    }

    @Id
    private String id;

    private String createDate;

    private OrderStatus status;

    private String buyerId;

    private String buyerName;

    private List<OrderItemReadModel> items;

    private int itemsCount;

    public OrderReadModel(String id, String createDate, OrderStatus status, String buyerId, String buyerName) {
        this.id = id;
        this.createDate = createDate;
        this.status = status;
        this.buyerId = buyerId;
        this.buyerName = buyerName;
        this.items = new ArrayList<>();
    }
}
