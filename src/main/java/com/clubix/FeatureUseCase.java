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
        return Mono.just((FeatureRequest) request)
                .flatMap(this::process);
    }

    private Mono<Response> process(FeatureRequest request) {
        if (request.name == null) return Mono.just(failure("name is required"));
        return Mono.just(success(request));
    }

    private Response success(FeatureRequest request) {
        return FeatureResponse.builder()
                .status("SUCCESS")
                .id(UUID.randomUUID().toString())
                .name(request.name)
                .build();
    }

    private Response failure(String reason) {
        return FeatureResponse.builder()
                .status("ERROR")
                .error(reason)
                .build();
    }
}
