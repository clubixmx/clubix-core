package com.clubix;

import com.clubix.boundary.FeatureRequest;
import com.clubix.boundary.FeatureResponse;
import com.clubix.boundary.Request;
import com.clubix.boundary.Response;
import reactor.core.publisher.Mono;
import java.util.UUID;

public class FeatureUseCase implements UseCase {
    @Override
    public Mono<Response> execute(Request request) {
        FeatureRequest featureRequest = (FeatureRequest) request;
        FeatureResponse response = new FeatureResponse();
        response.id = UUID.randomUUID().toString();
        response.name = featureRequest.name;
        return Mono.just(response);
    }
}
