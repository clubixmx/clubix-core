package com.clubix.usecase;

import com.clubix.usecase.model.request.Request;
import com.clubix.usecase.model.response.Response;
import reactor.core.publisher.Mono;

public interface UseCase {
    Mono<Response> execute(Request request);
}

