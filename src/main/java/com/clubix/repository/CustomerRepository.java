package com.clubix.repository;

import com.clubix.entity.Customer;
import reactor.core.publisher.Mono;

public interface CustomerRepository {
    Mono<Customer> findById(String customerId);
}
