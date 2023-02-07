package org.iaulitin.mongodbpg.configuration;

import org.iaulitin.mongodbpg.entity.Customer;
import org.iaulitin.mongodbpg.entity.CustomerAdditionalInfo;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;

import java.util.UUID;

import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertNotNull;

public class CustomerBeforeConvertListenerTest {

    @Nested
    class BeforeConvertTests {
        @Test
        void whenCreateThenUuidGenerated() {
            CustomerBeforeConvertListener listener = new CustomerBeforeConvertListener();
            Customer customer = Customer.builder()
                    .firstName("testFirstName")
                    .lastName("testLastName")
                    .additionalInfo(CustomerAdditionalInfo.builder().build())
                    .build();
            BeforeConvertEvent<Customer> event = new BeforeConvertEvent<>(customer, "customers");

            listener.onBeforeConvert(event);

            assertNotNull("Customer UUID should be generated during creation", customer.getUuid());
        }

        @Test
        void whenUpdateThenUuidNotGenerated() {
            CustomerBeforeConvertListener listener = new CustomerBeforeConvertListener();
            UUID expectedUuid = UUID.randomUUID();
            Customer customer = Customer.builder()
                    .uuid(expectedUuid)
                    .firstName("testFirstName")
                    .lastName("testLastName")
                    .additionalInfo(CustomerAdditionalInfo.builder().build())
                    .build();
            BeforeConvertEvent<Customer> event = new BeforeConvertEvent<>(customer, "customers");

            listener.onBeforeConvert(event);

            assertEquals("Customer UUID should not be changed during update",
                    expectedUuid, customer.getUuid());
        }
    }

}
