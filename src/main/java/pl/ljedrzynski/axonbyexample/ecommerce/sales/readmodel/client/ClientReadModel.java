package pl.ljedrzynski.axonbyexample.ecommerce.sales.readmodel.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import pl.ljedrzynski.axonbyexample.ecommerce.sales.domain.client.ClientData;

@Document
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ClientReadModel {

    @Id
    String clientId;
    String name;
}
