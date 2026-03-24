package com.clubix.bdd.steps;

import com.clubix.UseCase;
import com.clubix.UseCaseFactory;
import com.clubix.UseCaseFactoryImpl;
import com.clubix.boundary.Request;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;


public class ExampleSteps {

    private Mono<String> result;
    private UseCase useCase;
    private static final UseCaseFactory useCaseFactory = new UseCaseFactoryImpl();
    private Request request;


    @Given("I have a precondition")
    public void iHaveAPrecondition() {
        useCase = useCaseFactory.get("FeatureUseCase");
        request = new Request();
    }

    @When("I perform an action")
    public void iPerformAnAction() {
        result = useCase.execute(request);
    }

    @Then("I should see the expected result")
    public void iShouldSeeAResult() {
        StepVerifier.create(result)
                .expectNextMatches(response -> response.equals("success"))
                .verifyComplete();
    }
}
