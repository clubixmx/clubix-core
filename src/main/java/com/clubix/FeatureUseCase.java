package com.clubix;

import com.clubix.boundary.Request;
import reactor.core.publisher.Mono;

public class FeatureUseCase implements UseCase {
    @Override
    public Mono<String> execute(Request request) {
        return Mono.just("success");
    }
}
