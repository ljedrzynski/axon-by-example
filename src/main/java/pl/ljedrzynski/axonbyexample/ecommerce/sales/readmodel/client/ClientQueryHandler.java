package pl.ljedrzynski.axonbyexample.ecommerce.sales.readmodel.client;

import lombok.RequiredArgsConstructor;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;
import pl.ljedrzynski.axonbyexample.ecommerce.sales.application.queries.GetClientDetailsQuery;

@Component
@RequiredArgsConstructor
public class ClientQueryHandler {

    private final ClientReadModelRepository clientReadModelRepository;

    @QueryHandler
    public ClientReadModel handle(GetClientDetailsQuery query) {
        return clientReadModelRepository.findById(query.getClientId())
                .orElse(null);
    }
}
