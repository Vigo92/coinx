package com.vigo.coinx.repository;

import com.vigo.coinx.exceptions.ResourceNotFoundException;
import com.vigo.coinx.model.entity.AppUser;

public interface UserRepository {


     AppUser findByEmail(String email) throws ResourceNotFoundException;

      void save(AppUser appUser);

      int updateUser(AppUser appUser);
}
