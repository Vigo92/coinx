package com.vigo.coinx.service;

import com.vigo.coinx.exceptions.InvalidRequestException;
import com.vigo.coinx.exceptions.ResourceNotFoundException;
import com.vigo.coinx.model.entity.ConfirmationToken;

/**
 * @author :  Ugochukwu Vigo Obia
 * @project : coinx
 * @date :  10/02/2023
 * @email : obiaugochukwu@gmail.com, obiaugochukwu@icloud.com
 */
public interface ConfirmationTokenService {

    void saveConfirmationToken(ConfirmationToken confirmationToken);

    ConfirmationToken findByToken(String token) throws ResourceNotFoundException;

    int updateConfirmationToken(ConfirmationToken confirmationToken) throws ResourceNotFoundException, InvalidRequestException;
}
