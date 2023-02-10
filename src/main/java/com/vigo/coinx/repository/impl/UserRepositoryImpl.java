package com.vigo.coinx.repository.impl;

import com.vigo.coinx.model.entity.AppUser;
import com.vigo.coinx.repository.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;


public class UserRepositoryImpl implements UserRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public AppUser findByEmail(String email)  {
        AppUser appUser = entityManager.createNamedQuery(AppUser.FIND_USER_BY_EMAIL,AppUser.class).
                setParameter("email",email.toLowerCase()).getSingleResult();
        return appUser;
    }

    @Override
    public void save(AppUser appUser) {
         entityManager.persist(appUser);
    }
}
