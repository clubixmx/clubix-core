package com.clubix;

import com.clubix.boundary.Request;
import com.clubix.boundary.Response;
import reactor.core.publisher.Mono;

public interface UseCase {
    Mono<Response> execute(Request request);
}
