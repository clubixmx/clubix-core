package com.clubix.usecase.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ClubMember {
    String id;
    String name;
    String email;
}

