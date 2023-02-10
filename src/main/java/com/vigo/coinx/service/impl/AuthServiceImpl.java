package com.vigo.coinx.service.impl;

import com.vigo.coinx.exceptions.InvalidCredentialsException;
import com.vigo.coinx.exceptions.InvalidRequestException;
import com.vigo.coinx.exceptions.ResourceNotFoundException;
import com.vigo.coinx.exceptions.UserAlreadyExistsException;
import com.vigo.coinx.model.constant.Status;
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
import com.vigo.coinx.service.EmailService;
import com.vigo.coinx.service.UserService;
import com.vigo.coinx.util.ResponseCodes;
import com.vigo.coinx.util.SecurityUtil;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.UriInfo;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;


@RequestScoped
public class AuthServiceImpl implements AuthService, ResponseCodes {


    @Inject
    private UserService userService;

    @Inject
    private SecurityUtil securityUtil;

    @Inject
    private ConfirmationTokenService confirmationTokenService;

    @Inject
    private EmailService emailService;

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
        Map<String, String> hashedPassword = securityUtil.hashPassword(signupRequest.getPassword());
        appUser1.setPassword(hashedPassword.get("hashedPassword"));
        appUser1.setSalt(hashedPassword.get("salt"));
        userService.save(appUser1);
        AppUser appUser2 = userService.findByEmail(signupRequest.getEmail());
        ConfirmationToken confirmationToken = ConfirmationToken.builder().
                createdAt(LocalDateTime.now()).token(UUID.randomUUID().toString())
                .expiresAt(LocalDateTime.now().plusMinutes(15)).appUser(appUser2).build();
        confirmationTokenService.saveConfirmationToken(confirmationToken);
        emailService.sendEmail( appUser,  confirmationToken, uriInfo);
        return SignupResponse.builder().responseCode(SUCCESS_CODE).message(SUCCESSFUL_SIGN_UP_MESSAGE).status(Status.SUCCESS).build();
    }

    @Override
    public SignupResponse changePassword(ChangePasswordRequest changePasswordRequest, UriInfo uriInfo) throws ResourceNotFoundException {
        AppUser appUser = userService.findByEmail(changePasswordRequest.getEmail());
        String newPassword = changePasswordRequest.getNewPassword();
        Map<String, String> hashedPassword = securityUtil.hashPassword(newPassword);
        appUser.setPassword(hashedPassword.get("hashedPassword"));
        appUser.setSalt(hashedPassword.get("salt"));
        userService.updateUser(appUser);
        return SignupResponse.builder().status(Status.SUCCESS).responseCode(SUCCESS_CODE).message(SUCCESSFUL_PASSWORD_CHANGE).build();
    }

    @Override
    public SignupResponse confirmEmail(String token) throws ResourceNotFoundException, InvalidRequestException {
        ConfirmationToken confirmationToken = confirmationTokenService.findByToken(token);
        if(Objects.nonNull(confirmationToken.getConfirmedAt())){
            throw new InvalidRequestException("Token has already been used");
        }
        if(confirmationToken.getExpiresAt().isAfter(LocalDateTime.now())){
            throw new InvalidRequestException("Token has Expired");
        }
        confirmationToken.setConfirmedAt(LocalDateTime.now());
        confirmationTokenService.updateConfirmationToken(confirmationToken);
        return SignupResponse.builder().responseCode(SUCCESS_CODE).message(SUCCESSFUL_CONFIRM_EMAIL_MESSAGE).status(Status.SUCCESS).build();
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
