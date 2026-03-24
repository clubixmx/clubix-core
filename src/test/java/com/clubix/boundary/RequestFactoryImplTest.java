package com.clubix.boundary;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

class RequestFactoryImplTest {

    private static RequestFactory requestFactory;

    @BeforeAll
    static void setup() {
        requestFactory = new RequestFactoryImpl();
    }

    @Test
    void should_get_request_by_name() {
        Request request = requestFactory.get("FeatureRequest", null);
        Assertions.assertInstanceOf(FeatureRequest.class, request);
    }

    @Test
    void should_get_request_by_name_with_params() {
        Map<String,Object> params = new HashMap<>();
        params.put("name", "Jonathan");
        Request request = requestFactory.get("FeatureRequest", params);
        Assertions.assertInstanceOf(FeatureRequest.class, request);
        Assertions.assertEquals("Jonathan", ((FeatureRequest) request).name);
    }
}
