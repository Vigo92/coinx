package com.vigo.coinx.service;

import com.vigo.coinx.exceptions.ResourceNotFoundException;
import com.vigo.coinx.model.entity.AppUser;

public interface UserService {



    boolean authenticateUser(String email, String password) throws ResourceNotFoundException;

    AppUser findByEmail(String email) throws ResourceNotFoundException;

    void save(AppUser appUser);

    int updateUser(AppUser appUser);
}
