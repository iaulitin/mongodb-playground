package org.iaulitin.mongodbpg.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.iaulitin.mongodbpg.dto.CustomerCreateRequest;
import org.iaulitin.mongodbpg.dto.CustomerResponse;
import org.iaulitin.mongodbpg.dto.CustomerUpdateRequest;
import org.iaulitin.mongodbpg.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestController
@RequiredArgsConstructor
@Slf4j
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/customer/{uuid}")
    public CustomerResponse getCustomers(@PathVariable("uuid") UUID uuid) {
        return customerService.getCustomers(uuid);
    }

    @GetMapping("/customer")
    public List<CustomerResponse> getCustomers(@RequestParam(defaultValue = "0") int page,
                                               @RequestParam(defaultValue = "10") int size) {
        return customerService.getCustomers(page, size);
    }

    @GetMapping("/customer/search")
    public List<CustomerResponse> findCustomers(@RequestParam("firstName") String firstName,
                                                @RequestParam(defaultValue = "0") int page,
                                                @RequestParam(defaultValue = "10") int size) {
        return customerService.getCustomers(firstName, page, size);
    }

    @PostMapping("/customer")
    public CustomerResponse createCustomer(@RequestBody CustomerCreateRequest customerCreateRequest) {
        return customerService.createCustomer(customerCreateRequest);
    }

    @PutMapping("/customer/{uuid}")
    public CustomerResponse updateCustomer(@PathVariable("uuid") UUID uuid,
                                           @RequestBody CustomerUpdateRequest customerCreateRequest) {
        return customerService.updateCustomer(uuid, customerCreateRequest);
    }

    @ExceptionHandler(RuntimeException.class)
    private ResponseEntity<?> handleException(RuntimeException ex) {
        log.error("An exception happened while processing a request", ex);
        return ResponseEntity.internalServerError().body(INTERNAL_SERVER_ERROR);
    }
}
