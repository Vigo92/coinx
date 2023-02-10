package com.vigo.coinx.service.impl;

import com.vigo.coinx.exceptions.ResourceNotFoundException;
import com.vigo.coinx.model.entity.AppUser;
import com.vigo.coinx.repository.UserRepository;
import com.vigo.coinx.service.UserService;
import com.vigo.coinx.util.SecurityUtil;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;


@RequestScoped
@Transactional
public class UserServiceImpl implements UserService {

    @Inject
    private UserRepository userRepository;

    @Inject
    private SecurityUtil securityUtil;
    @Override
    public boolean authenticateUser(String email, String password) throws ResourceNotFoundException {
        AppUser appUser = userRepository.findByEmail(email);
        if(Objects.isNull(appUser)){
            throw new ResourceNotFoundException();
        }
        return securityUtil.passwordsMatch(appUser.getPassword(),appUser.getSalt(),password);
    }

    @Override
    public AppUser findByEmail(String email) throws ResourceNotFoundException {
        return userRepository.findByEmail(email);
    }

    @Override
    public void save( @NotNull AppUser appUser) {
         userRepository.save(appUser);
    }
}
