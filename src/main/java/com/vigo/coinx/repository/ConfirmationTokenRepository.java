package com.vigo.coinx.repository;

import com.vigo.coinx.model.entity.ConfirmationToken;

/**
 * @author :  Ugochukwu Vigo Obia
 * @project : coinx
 * @date :  10/02/2023
 * @email : obiaugochukwu@gmail.com, obiaugochukwu@icloud.com
 */
public interface ConfirmationTokenRepository {

    ConfirmationToken findByToken(String token);

    void saveConfirmationToken(ConfirmationToken confirmationToken);

    int updateConfirmationToken(ConfirmationToken confirmationToken);
}
