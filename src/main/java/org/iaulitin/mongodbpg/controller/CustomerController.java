package org.iaulitin.mongodbpg.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.iaulitin.mongodbpg.entity.Customer;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.iaulitin.mongodbpg.dao.CustomerRepository;

import java.util.List;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestController
@RequiredArgsConstructor
@Slf4j
public class CustomerController {

    private final CustomerRepository customerRepository;

    @GetMapping("/customer")
    public List<Customer> getCustomers(@RequestParam(defaultValue = "0") int page,
                                       @RequestParam(defaultValue = "10") int size) {
        Pageable paging = PageRequest.of(page, size);
        return customerRepository.findAll(paging).stream().toList();
    }

    @GetMapping("/customer/search")
    public Customer findCustomer(@RequestParam("firstName") String firstName) {
        return customerRepository.findByFirstName(firstName);
    }

    @PostMapping("/customer")
    public Customer createCustomer(@RequestBody Customer customer) {
        return customerRepository.save(customer);
    }

    @ExceptionHandler(RuntimeException.class)
    private ResponseEntity<?> handleException(RuntimeException ex) {
        log.error("An exception happened while processing a request", ex);
        return ResponseEntity.internalServerError().body(INTERNAL_SERVER_ERROR);
    }
}
