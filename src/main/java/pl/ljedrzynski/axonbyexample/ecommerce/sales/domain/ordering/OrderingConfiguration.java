package pl.ljedrzynski.axonbyexample.ecommerce.sales.domain.ordering;


import org.axonframework.common.caching.Cache;
import org.axonframework.common.caching.WeakReferenceCache;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.modelling.command.Repository;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class OrderingConfiguration {

    @Bean
    public Repository<Order> orderRepository(EventStore eventStore, Cache orderCache) {
        return EventSourcingRepository.builder(Order.class)
                .cache(orderCache)
                .eventStore(eventStore)
                .build();
    }

    @Bean
    public Cache orderCache() {
        return new WeakReferenceCache();
    }
}
