package com.clubix.usecase;

import com.clubix.entity.Customer;
import com.clubix.repository.CustomerRepository;
import com.clubix.usecase.model.request.QueryBalanceRequest;
import com.clubix.usecase.model.response.QueryBalanceResponse;
import com.usecase.AbstractUseCase;
import com.usecase.model.response.Response;
import com.usecase.shared.ValidationException;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class QueryBalanceUseCase extends AbstractUseCase<QueryBalanceRequest> {
    private final CustomerRepository customerRepository;
    // ── Guard ─────────────────────────────────────────────────────────────────

    @Override
    protected Mono<QueryBalanceRequest> guard(QueryBalanceRequest request) {
        return Mono.just(request)
                .flatMap(this::customerIdMustBePresent);
    }

    private Mono<QueryBalanceRequest> customerIdMustBePresent(QueryBalanceRequest request) {
        if (request.customerId == null) return Mono.error(new ValidationException("customerId is required"));
        return Mono.just(request);
    }

    // ── Process ───────────────────────────────────────────────────────────────

    @Override
    protected Mono<Response> process(QueryBalanceRequest request) {
        return customerRepository.findById(request.customerId)
                .map(this::success);
    }

    // ── Response builders ─────────────────────────────────────────────────────

    private Response success(Customer customer) {
        return QueryBalanceResponse.builder()
                .status("SUCCESS")
                .balance(customer.getBalance())
                .build();
    }
}
