package com.clubix.usecase;

import com.clubix.usecase.model.request.Request;
import com.clubix.usecase.model.response.Response;
import com.clubix.usecase.shared.ValidationException;
import reactor.core.publisher.Mono;

public abstract class AbstractUseCase<R extends Request> implements UseCase {

    @Override
    @SuppressWarnings("unchecked")
    public final Mono<Response> execute(Request request) {
        return Mono.just((R) request)
                .flatMap(this::guard)
                .flatMap(this::process)
                .onErrorResume(ValidationException.class, e -> Mono.just(failure(e.getMessage())));
    }

    // ── Override to add validations and preconditions ─────────────────────────

    protected Mono<R> guard(R request) {
        return Mono.just(request);
    }

    // ── Must implement: core business logic ───────────────────────────────────

    protected abstract Mono<Response> process(R request);

    // ── Override to customize the failure response ────────────────────────────

    protected Response failure(String reason) {
        return Response.builder()
                .status("ERROR")
                .error(reason)
                .build();
    }
}

