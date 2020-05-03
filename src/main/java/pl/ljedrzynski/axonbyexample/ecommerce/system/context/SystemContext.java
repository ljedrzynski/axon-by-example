package pl.ljedrzynski.axonbyexample.ecommerce.system.context;

import org.springframework.stereotype.Component;

@Component
public class SystemContext {

    public UserContext getLoggedUser() {
        // TODO Implement user context
        return new UserContext("client-1", "user");
    }
}
