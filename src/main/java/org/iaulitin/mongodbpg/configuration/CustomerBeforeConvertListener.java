package org.iaulitin.mongodbpg.configuration;

import lombok.extern.slf4j.Slf4j;
import org.iaulitin.mongodbpg.entity.Customer;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
public class CustomerBeforeConvertListener extends AbstractMongoEventListener<Customer> {

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Customer> event) {
        super.onBeforeConvert(event);

        Customer customer = event.getSource();
        if (customer.getUuid() == null) {
            customer.setUuid(UUID.randomUUID());
            log.debug("Creating a customer {} using onBeforeConvert", customer);
        }
    }
}
