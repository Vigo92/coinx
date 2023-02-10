package com.vigo.coinx.controller;


import com.vigo.coinx.exceptions.InvalidCredentialsException;
import com.vigo.coinx.exceptions.ResourceNotFoundException;
import com.vigo.coinx.model.request.ChangePasswordRequest;
import com.vigo.coinx.model.request.LoginRequest;
import com.vigo.coinx.model.request.SignupRequest;
import com.vigo.coinx.model.response.LoginResponse;
import com.vigo.coinx.model.response.SignupResponse;
import com.vigo.coinx.service.AuthService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/auth")
@RequestScoped
public class AuthController {



    @Context
    private UriInfo uriInfo;

    @Inject
    private AuthService authService;


    @POST
    @Path("/login")
    public Response login(@Valid LoginRequest loginRequest) throws InvalidCredentialsException, ResourceNotFoundException {
        LoginResponse loginResponse = authService.login(loginRequest, uriInfo);
        return Response.ok(loginResponse).build();
    }

    @POST
    @Path("/login")
    public Response register(@Valid SignupRequest signupRequest) throws InvalidCredentialsException, ResourceNotFoundException {
        SignupResponse signupResponse = authService.signUp(signupRequest, uriInfo);
        return Response.ok(signupResponse).build();
    }

    @POST
    @Path("/login")
    public Response changePassword(@Valid ChangePasswordRequest changePasswordRequest) throws InvalidCredentialsException, ResourceNotFoundException {
        SignupResponse signupResponse = authService.changePassword(changePasswordRequest, uriInfo);
        return Response.ok(signupResponse).build();
    }

    @GET
    @Path("/confirm-email/{token}")
    public Response confirmEmail(@PathParam("token") String token) throws InvalidCredentialsException, ResourceNotFoundException {
        SignupResponse signupResponse = authService.confirmEmail(token);
        return Response.ok(signupResponse).build();
    }
}
