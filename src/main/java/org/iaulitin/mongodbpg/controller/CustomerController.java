package org.iaulitin.mongodbpg.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.iaulitin.mongodbpg.common.exceptions.ResourceNotFoundException;
import org.iaulitin.mongodbpg.dto.CustomerCreateRequest;
import org.iaulitin.mongodbpg.dto.CustomerResponse;
import org.iaulitin.mongodbpg.dto.CustomerUpdateRequest;
import org.iaulitin.mongodbpg.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/customer")
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/{uuid}")
    public CustomerResponse getCustomers(@PathVariable("uuid") UUID uuid) {
        return customerService.getCustomer(uuid);
    }

    @GetMapping
    public List<CustomerResponse> getCustomers(@RequestParam(defaultValue = "0") int page,
                                               @RequestParam(defaultValue = "10") int size) {
        return customerService.getCustomers(page, size);
    }

    @GetMapping("/search")
    public List<CustomerResponse> findCustomers(@RequestParam("firstName") String firstName,
                                                @RequestParam(defaultValue = "0") int page,
                                                @RequestParam(defaultValue = "10") int size) {
        return customerService.findCustomers(firstName, page, size);
    }

    @PostMapping
    public CustomerResponse createCustomer(@RequestBody CustomerCreateRequest customerCreateRequest) {
        return customerService.createCustomer(customerCreateRequest);
    }

    @PutMapping("/{uuid}")
    public CustomerResponse updateCustomer(@PathVariable("uuid") UUID uuid,
                                           @RequestBody CustomerUpdateRequest customerCreateRequest) {
        return customerService.updateCustomer(uuid, customerCreateRequest);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @SuppressWarnings("PMD.UnusedPrivateMethod")
    private ResponseEntity<?> handleException(ResourceNotFoundException ex) {
        log.debug("The requested resource does not exist", ex);
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(RuntimeException.class)
    @SuppressWarnings("PMD.UnusedPrivateMethod")
    private ResponseEntity<?> handleException(RuntimeException ex) {
        log.error("An exception happened while processing a request", ex);
        return ResponseEntity.internalServerError().build();
    }
}
