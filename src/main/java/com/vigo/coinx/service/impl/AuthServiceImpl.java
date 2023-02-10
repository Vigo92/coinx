package com.vigo.coinx.service.impl;

import com.vigo.coinx.exceptions.InvalidCredentialsException;
import com.vigo.coinx.exceptions.ResourceNotFoundException;
import com.vigo.coinx.exceptions.UserAlreadyExistsException;
import com.vigo.coinx.model.dto.UserDTO;
import com.vigo.coinx.model.entity.AppUser;
import com.vigo.coinx.model.entity.ConfirmationToken;
import com.vigo.coinx.model.request.ChangePasswordRequest;
import com.vigo.coinx.model.request.LoginRequest;
import com.vigo.coinx.model.request.SignupRequest;
import com.vigo.coinx.model.response.LoginResponse;
import com.vigo.coinx.model.response.SignupResponse;
import com.vigo.coinx.service.AuthService;
import com.vigo.coinx.service.ConfirmationTokenService;
import com.vigo.coinx.service.UserService;
import com.vigo.coinx.util.SecurityUtil;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.UriInfo;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;


@RequestScoped
public class AuthServiceImpl implements AuthService {


    @Inject
    private UserService userService;

    @Inject
    private SecurityUtil securityUtil;

    @Inject
    private ConfirmationTokenService confirmationTokenService;

    @Override
    public LoginResponse login(LoginRequest loginRequest, UriInfo uriInfo) throws InvalidCredentialsException, ResourceNotFoundException {
        boolean isValidCredentials = userService.authenticateUser(loginRequest.getUsername(), loginRequest.getPassword());
        if(!isValidCredentials){
            throw new InvalidCredentialsException();
        }
        AppUser appUser = userService.findByEmail(loginRequest.getUsername());
        String token = securityUtil.getToken(loginRequest.getUsername(),uriInfo);
        return LoginResponse.builder().token(token).user(convertEntityToData(appUser))
                .build();
    }

    @Override
    public SignupResponse signUp(SignupRequest signupRequest, UriInfo uriInfo) throws ResourceNotFoundException, UserAlreadyExistsException {
        AppUser appUser = userService.findByEmail(signupRequest.getEmail());
        if(Objects.nonNull(appUser)){
            throw new UserAlreadyExistsException();
        }
        AppUser appUser1 = convertDataToEntity(signupRequest);
        userService.save(appUser1);
        AppUser appUser2 = userService.findByEmail(signupRequest.getEmail());
        ConfirmationToken confirmationToken = ConfirmationToken.builder().
                createdAt(LocalDateTime.now()).token(UUID.randomUUID().toString())
                .expiresAt(LocalDateTime.now().plusMinutes(15)).appUser(appUser2).build();
        co
        return null;
    }

    @Override
    public SignupResponse changePassword(ChangePasswordRequest changePasswordRequest, UriInfo uriInfo) {
        return null;
    }

    @Override
    public SignupResponse confirmEmail(String token) {
        return null;
    }


    public UserDTO convertEntityToData(AppUser appUser){
        return UserDTO.builder().lastName(appUser.getLastName()).firstName(appUser.getFirstName())
                .email(appUser.getEmail())
                .build();
    }
    public AppUser convertDataToEntity(SignupRequest signupRequest){
        return AppUser.builder().lastName(signupRequest.getLastName()).firstName(signupRequest.getFirstName())
                .email(signupRequest.getEmail()).password(signupRequest.getPassword())
                .build();
    }
}
