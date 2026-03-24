package com.clubix.boundary;

import java.lang.reflect.Field;
import java.util.Map;

public class RequestFactoryImpl implements RequestFactory {
    @Override
    public Request get(String requestName, Map<String, Object> params) {
        if ("FeatureRequest".equals(requestName)) {
            return populate(new FeatureRequest(), params);
        }
        throw new IllegalArgumentException("Unknown request: " + requestName);
    }

    private <T extends Request> T populate(T request, Map<String, Object> params) {
        if (params == null) return request;
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            try {
                Field field = request.getClass().getField(entry.getKey());
                field.set(request, entry.getValue());
            } catch (NoSuchFieldException | IllegalAccessException ignored) {
            }
        }
        return request;
    }
}
