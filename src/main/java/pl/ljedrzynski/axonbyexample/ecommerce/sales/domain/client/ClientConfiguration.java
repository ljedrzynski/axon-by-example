package pl.ljedrzynski.axonbyexample.ecommerce.sales.domain.client;


import org.axonframework.common.caching.Cache;
import org.axonframework.common.caching.WeakReferenceCache;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.modelling.command.Repository;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ClientConfiguration {

    @Bean
    public Repository<Client> clientRepository(EventStore eventStore, Cache clientCache) {
        return EventSourcingRepository.builder(Client.class)
                .cache(clientCache)
                .eventStore(eventStore)
                .build();
    }

    @Bean
    public Cache clientCache() {
        return new WeakReferenceCache();
    }
}
