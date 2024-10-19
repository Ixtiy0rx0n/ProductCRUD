package org.example.exp;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

@Provider
public class AppBadExceptionMapper implements ExceptionMapper<AppBadException> {
    @Override
    public Response toResponse(AppBadException exception) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", new Date());
        body.put("message", exception.getMessage());
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(body)
                .build();
    }
}
