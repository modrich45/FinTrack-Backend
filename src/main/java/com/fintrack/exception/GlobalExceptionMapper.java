package com.fintrack.exception;

import com.fintrack.commonDTO.ErrorMessage;

import jakarta.ws.rs.ext.Provider;

@Provider
public class GlobalExceptionMapper implements jakarta.ws.rs.ext.ExceptionMapper<ResourceNotFound> {
    @Override
    public jakarta.ws.rs.core.Response toResponse(ResourceNotFound exception) {
        ErrorMessage errorMessage = new ErrorMessage(exception.getMessage());

        return jakarta.ws.rs.core.Response.status(jakarta.ws.rs.core.Response.Status.NOT_FOUND)
                .entity(errorMessage)
                .build();
    }

}
