package com.clubix.bdd.steps.business;

import com.clubix.entity.Customer;
import com.clubix.repository.CustomerRepository;
import com.clubix.usecase.QueryBalanceUseCase;
import com.clubix.usecase.model.response.QueryBalanceResponse;
import com.usecase.UseCase;
import com.usecase.model.request.Request;
import com.usecase.model.request.RequestFactory;
import com.usecase.model.request.RequestFactoryImpl;
import com.usecase.model.response.Response;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Map;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BusinessSteps {

    private static final Double expectedBalance = 130.0;
    private static final RequestFactory requestFactory =
            new RequestFactoryImpl("com.clubix.usecase.model.request");

    private CustomerRepository customerRepository;
    private UseCase queryBalanceUseCase;
    private Mono<Response> response;

    @Before
    public void setup() {
        customerRepository = mock(CustomerRepository.class);
        queryBalanceUseCase = new QueryBalanceUseCase(customerRepository);
    }

    @Given("a customer with ID {string} exists")
    public void a_customer_with_id_exists(String customerId) {
        Customer customer = Customer.builder()
                .id(customerId)
                .balance(expectedBalance)
                .build();
        when(customerRepository.findById(customerId)).thenReturn(Mono.just(customer));
    }

    @When("I query the balance for customer ID {string}")
    public void i_query_the_balance_for_customer_id(String customerId) {
        Request request = requestFactory.get("QueryBalanceRequest", Map.of("customerId", customerId));
        response = queryBalanceUseCase.execute(request);
    }

    @Then("the response should contain the balance information for customer ID {string}")
    public void the_response_should_contain_the_balance_information_for_customer_id(String customerId) {
        StepVerifier.create(response)
                .assertNext(r -> {
                    Assertions.assertInstanceOf(QueryBalanceResponse.class, r);
                    QueryBalanceResponse qbr = (QueryBalanceResponse) r;
                    Assertions.assertEquals("SUCCESS", qbr.status);
                    Assertions.assertEquals(expectedBalance, qbr.balance);
                })
                .verifyComplete();
    }
}
