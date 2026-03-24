package com.clubix;

import com.clubix.boundary.Request;
import reactor.core.publisher.Mono;

public class FeatureUseCase implements UseCase {
    @Override
    public Mono<String> execute(Request request) {
        // Implementa la lógica del caso de uso aquí
        return Mono.just("success");
    }
}
