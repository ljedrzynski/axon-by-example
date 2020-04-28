package pl.ljedrzynski.axonbyexample.ecommerce.sales.domain.ordering

import org.axonframework.test.aggregate.AggregateTestFixture
import org.axonframework.test.aggregate.FixtureConfiguration
import pl.ljedrzynski.axonbyexample.ecommerce.sales.application.commands.CreateOrderCommand
import pl.ljedrzynski.axonbyexample.ecommerce.sales.domain.client.ClientData
import pl.ljedrzynski.axonbyexample.ecommerce.sales.domain.ordering.events.OrderCreatedEvent
import spock.lang.Specification

import java.time.LocalDateTime

class OrderTests extends Specification {


    private FixtureConfiguration<Order> fixture


    void setup() {
        fixture = new AggregateTestFixture<>(Order.class)
    }

    def  'should create order aggregate'() {
        when:
            def orderId = "orderId";
            def clientId = "clientId";
            def clientName = "Test client";
            def createDate = LocalDateTime.of(2020, 01, 01, 12, 00, 00);
        then:
            fixture.givenNoPriorActivity()
                    .when( new CreateOrderCommand(orderId, new ClientData(clientId, clientName), createDate))
                    .expectSuccessfulHandlerExecution()
                    .expectEvents(new OrderCreatedEvent(orderId, new ClientData(clientId, clientName), createDate
                    ))
                    .expectState({ state ->
                        state.id == orderId
                        state.status == Order.OrderStatus.OPEN
                        state.createDate == createDate
                        state.clientData == new ClientData(clientId, clientName)
                    })
    }
}
