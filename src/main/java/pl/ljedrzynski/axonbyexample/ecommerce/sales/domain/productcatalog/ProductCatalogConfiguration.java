package pl.ljedrzynski.axonbyexample.ecommerce.sales.domain.productcatalog;


import org.axonframework.common.caching.Cache;
import org.axonframework.common.caching.WeakReferenceCache;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.modelling.command.Repository;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ProductCatalogConfiguration {

    @Bean
    public Repository<Product> productRepository(EventStore eventStore, Cache productCache) {
        return EventSourcingRepository.builder(Product.class)
                .cache(productCache)
                .eventStore(eventStore)
                .build();
    }

    @Bean
    public Cache productCache() {
        return new WeakReferenceCache();
    }
}
