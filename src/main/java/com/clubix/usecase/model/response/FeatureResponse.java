package com.clubix.usecase.model.response;

import lombok.experimental.SuperBuilder;

@SuperBuilder
public class FeatureResponse extends Response {
    public String id;
    public String name;
}

