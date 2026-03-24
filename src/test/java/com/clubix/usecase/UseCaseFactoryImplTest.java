package com.clubix.usecase;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class UseCaseFactoryImplTest {

    private static UseCaseFactory useCaseFactory;

    @BeforeAll
    static void setup() {
        useCaseFactory = new UseCaseFactoryImpl();
    }

    @Test
    void should_get_feature_use_case_by_name() {
        UseCase useCase = useCaseFactory.get("FeatureUseCase");
        Assertions.assertNotNull(useCase);
        Assertions.assertInstanceOf(FeatureUseCase.class, useCase);
    }

    @Test
    void should_throw_exception_if_feature_use_case_not_found() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
            useCaseFactory.get("UnknownUseCase")
        );
    }
}

