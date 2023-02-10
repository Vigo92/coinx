package com.vigo.coinx.repository.impl;

import com.vigo.coinx.model.entity.ConfirmationToken;
import com.vigo.coinx.repository.ConfirmationTokenRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

/**
 * @author :  Ugochukwu Vigo Obia
 * @project : coinx
 * @date :  10/02/2023
 * @email : obiaugochukwu@gmail.com, obiaugochukwu@icloud.com
 */
public class ConfirmationTokenRepositoryImpl implements ConfirmationTokenRepository {


    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public ConfirmationToken findByToken(String token) {
        return entityManager.createNamedQuery(ConfirmationToken.FIND_BY_TOKEN,ConfirmationToken.class)
                .setParameter("token",token).getSingleResult();
    }

    @Override
    public void saveConfirmationToken(ConfirmationToken confirmationToken) {
         entityManager.persist(confirmationToken);
    }

    @Override
    public int updateConfirmationToken(ConfirmationToken confirmationToken) {
        entityManager.merge(confirmationToken);
        return 0;
    }
}
