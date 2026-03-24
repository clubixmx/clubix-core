package com.clubix.bdd.steps;

import com.clubix.UseCase;
import com.clubix.UseCaseFactory;
import com.clubix.UseCaseFactoryImpl;
import com.clubix.boundary.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;


public class ExampleSteps {

    private Mono<Response> result;
    private UseCase useCase;
    private static final UseCaseFactory useCaseFactory = new UseCaseFactoryImpl();
    private static final RequestFactory requestFactory = new RequestFactoryImpl();
    private Request request;


    @Given("I have a precondition")
    public void iHaveAPrecondition() {
        useCase = useCaseFactory.get("FeatureUseCase");
        request = requestFactory.get("FeatureRequest", java.util.Map.of("name", "Jonathan"));
    }

    @When("I perform an action")
    public void iPerformAnAction() {
        result = useCase.execute(request);
    }

    @Then("I should see the expected result")
    public void iShouldSeeAResult() {
        StepVerifier.create(result)
                .expectNextMatches(response -> response instanceof FeatureResponse)
                .verifyComplete();
    }
}
