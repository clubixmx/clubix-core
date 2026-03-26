package com.clubix.usecase;

import com.clubix.entity.Customer;
import com.clubix.repository.CustomerRepository;
import com.clubix.usecase.model.request.QueryBalanceRequest;
import com.clubix.usecase.model.response.QueryBalanceResponse;
import com.usecase.shared.ValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class QueryBalanceUseCaseTest {

    private CustomerRepository customerRepository;
    private QueryBalanceUseCase useCase;

    @BeforeEach
    void setup() {
        customerRepository = mock(CustomerRepository.class);
        useCase = new QueryBalanceUseCase(customerRepository);
    }

    @Test
    void should_return_balance_when_customer_exists() {
        String customerId = "C-001";
        Double expectedBalance = 250.0;

        when(customerRepository.findById(customerId))
                .thenReturn(Mono.just(Customer.builder()
                        .id(customerId)
                        .balance(expectedBalance)
                        .build()));

        QueryBalanceRequest request = QueryBalanceRequest.builder()
                .customerId(customerId)
                .build();

        StepVerifier.create(useCase.execute(request))
                .assertNext(response -> {
                    QueryBalanceResponse qbr = (QueryBalanceResponse) response;
                    org.junit.jupiter.api.Assertions.assertEquals("SUCCESS", qbr.status);
                    org.junit.jupiter.api.Assertions.assertEquals(expectedBalance, qbr.balance);
                })
                .verifyComplete();
    }

    @Test
    void should_signal_error_when_repository_throws_exception() {
        String customerId = "C-001";
        RuntimeException dbError = new RuntimeException("DB connection failed");

        when(customerRepository.findById(customerId))
                .thenReturn(Mono.error(dbError));

        QueryBalanceRequest request = QueryBalanceRequest.builder()
                .customerId(customerId)
                .build();

        StepVerifier.create(useCase.execute(request))
                .verifyErrorMatches(e ->
                        e instanceof RuntimeException &&
                        "DB connection failed".equals(e.getMessage()));
    }

    @Test
    void should_signal_error_when_customer_not_found() {
        String customerId = "C-999";

        when(customerRepository.findById(customerId))
                .thenReturn(Mono.empty());

        QueryBalanceRequest request = QueryBalanceRequest.builder()
                .customerId(customerId)
                .build();

        StepVerifier.create(useCase.execute(request))
                .verifyErrorMatches(e ->
                        e instanceof ValidationException &&
                        e.getMessage().contains(customerId));
    }
}
