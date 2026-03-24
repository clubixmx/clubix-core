package com.clubix;

import com.clubix.boundary.Request;
import reactor.core.publisher.Mono;

public interface UseCase {
    Mono<String> execute(Request request);
}
