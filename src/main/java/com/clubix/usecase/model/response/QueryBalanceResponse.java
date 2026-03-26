package com.clubix.usecase.model.response;

import com.usecase.model.response.Response;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor
public class QueryBalanceResponse extends Response {
    public Double balance;
}
