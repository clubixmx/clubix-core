package com.clubix;

import com.clubix.boundary.FeatureResponse;
import com.clubix.boundary.Request;
import com.clubix.boundary.RequestFactory;
import com.clubix.boundary.RequestFactoryImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;
import java.util.Map;

class FeatureUseCaseTest {

    private static UseCaseFactory useCaseFactory;
    private static RequestFactory requestFactory;

    @BeforeAll
    static void setup() {
        useCaseFactory = new UseCaseFactoryImpl();
        requestFactory = new RequestFactoryImpl();
    }

    @Test
    void should_return_success_when_name_is_provided() {
        //Arrange
        UseCase useCase = useCaseFactory.get("FeatureUseCase");
        Request featureRequest = requestFactory.get("FeatureRequest", Map.of("name", "Jonathan"));
        //Execute & Assert
        StepVerifier.create(useCase.execute(featureRequest))
                .assertNext(response -> {
                    Assertions.assertInstanceOf(FeatureResponse.class, response);
                    FeatureResponse featureResponse = (FeatureResponse) response;
                    Assertions.assertEquals("SUCCESS", featureResponse.status);
                    Assertions.assertNotNull(featureResponse.id);
                    Assertions.assertEquals("Jonathan", featureResponse.name);
                })
                .verifyComplete();
    }

    @Test
    void should_return_error_when_name_is_null() {
        //Arrange
        UseCase useCase = useCaseFactory.get("FeatureUseCase");
        Request featureRequest = requestFactory.get("FeatureRequest", Map.of());
        //Execute & Assert
        StepVerifier.create(useCase.execute(featureRequest))
                .assertNext(response -> {
                    Assertions.assertInstanceOf(FeatureResponse.class, response);
                    FeatureResponse featureResponse = (FeatureResponse) response;
                    Assertions.assertEquals("ERROR", featureResponse.status);
                    Assertions.assertNotNull(featureResponse.error);
                })
                .verifyComplete();
    }
}
