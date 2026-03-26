package com.clubix.usecase.model.request;

import com.usecase.model.request.Request;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor
public class QueryBalanceRequest extends Request {
    public String customerId;
}
