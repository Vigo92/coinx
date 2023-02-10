package com.vigo.coinx.exceptions.mapper;

import com.vigo.coinx.exceptions.InvalidRequestException;
import jakarta.ws.rs.NotAuthorizedException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author :  Ugochukwu Vigo Obia
 * @project : coinx
 * @date :  10/02/2023
 * @email : obiaugochukwu@gmail.com, obiaugochukwu@icloud.com
 */


@Provider
public class InvalidRequestExceptionMapper implements ExceptionMapper<InvalidRequestException> {
    @Override
    public Response toResponse(InvalidRequestException exception) {
        return Response.status(Response.Status.BAD_REQUEST).entity(exception).build();
    }
}
