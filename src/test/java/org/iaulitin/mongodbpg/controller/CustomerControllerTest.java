package org.iaulitin.mongodbpg.controller;

import org.iaulitin.mongodbpg.common.exceptions.ResourceNotFoundException;
import org.iaulitin.mongodbpg.dto.CustomerResponse;
import org.iaulitin.mongodbpg.service.CustomerService;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CustomerController.class)
@AutoConfigureMockMvc
@SuppressWarnings("PMD.JUnitTestsShouldIncludeAssert")
class CustomerControllerTest {

    @MockBean
    private CustomerService customerService;

    @Autowired
    private MockMvc mockMvc;

    @Nested
    class GetCustomer {
        @Test
        void getCustomersHappyPath() throws Exception {
            String uuid = UUID.randomUUID().toString();
            CustomerResponse customerResponse = CustomerResponse.builder()
                    .id(uuid)
                    .firstName("firstName")
                    .lastName("lastName")
                    .build();

            when(customerService.getCustomer(eq(uuid))).thenReturn(customerResponse);

            MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/api/v1/customer/" + uuid);
            mockMvc.perform(request)
                    .andDo(print())
                    .andExpect(status().is2xxSuccessful())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.uuid").value(uuid.toString()))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("firstName"))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("lastName"));
        }

        @Test
        void getCustomersIfNoCustomerThen404() throws Exception {
            String uuid = UUID.randomUUID().toString();

            when(customerService.getCustomer(eq(uuid))).thenThrow(new ResourceNotFoundException());

            MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/api/v1/customer/" + uuid);
            mockMvc.perform(request)
                    .andDo(print())
                    .andExpect(status().isNotFound());
        }
    }
}