package com.vigo.coinx.service;

import com.vigo.coinx.exceptions.InvalidCredentialsException;
import com.vigo.coinx.exceptions.InvalidRequestException;
import com.vigo.coinx.exceptions.ResourceNotFoundException;
import com.vigo.coinx.exceptions.UserAlreadyExistsException;
import com.vigo.coinx.model.request.ChangePasswordRequest;
import com.vigo.coinx.model.request.LoginRequest;
import com.vigo.coinx.model.request.SignupRequest;
import com.vigo.coinx.model.response.LoginResponse;
import com.vigo.coinx.model.response.SignupResponse;
import jakarta.ws.rs.core.UriInfo;

public interface AuthService {
    LoginResponse login(LoginRequest loginRequest, UriInfo uriInfo) throws InvalidCredentialsException, ResourceNotFoundException;

    SignupResponse signUp(SignupRequest signupRequest, UriInfo uriInfo) throws ResourceNotFoundException, UserAlreadyExistsException;

    SignupResponse changePassword(ChangePasswordRequest changePasswordRequest, UriInfo uriInfo) throws ResourceNotFoundException;

    SignupResponse confirmEmail(String token) throws ResourceNotFoundException, InvalidRequestException;
}
