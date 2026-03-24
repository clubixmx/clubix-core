package com.clubix.boundary;

import java.util.Map;

public interface RequestFactory {
        Request get(String requestName, Map<String,Object> params);
}
