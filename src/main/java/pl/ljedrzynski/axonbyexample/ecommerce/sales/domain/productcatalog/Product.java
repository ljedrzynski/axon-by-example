package pl.ljedrzynski.axonbyexample.ecommerce.sales.domain.productcatalog;

import org.axonframework.modelling.command.AggregateIdentifier;
import pl.ljedrzynski.axonbyexample.ecommerce.shared.cannonicalmodel.Money;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Product {

    @Id
    @GeneratedValue
    @AggregateIdentifier
    private String id;

    private String name;

    @Enumerated(EnumType.STRING)
    private ProductType type;

    @Embedded
    @AttributeOverrides(value = {
            @AttributeOverride(name = "denomination", column = @Column(name = "price_value")),
            @AttributeOverride(name = "currencyCode", column = @Column(name = "price_currency"))
    })
    private Money price;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ProductType getType() {
        return type;
    }

    public Money getPrice() {
        return price;
    }

    public ProductData getSnapshot() {
        return new ProductData(id, name, type, price);
    }
}
