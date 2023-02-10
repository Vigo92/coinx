package com.vigo.coinx.service.impl;

import com.vigo.coinx.exceptions.InvalidRequestException;
import com.vigo.coinx.exceptions.ResourceNotFoundException;
import com.vigo.coinx.model.entity.ConfirmationToken;
import com.vigo.coinx.repository.ConfirmationTokenRepository;
import com.vigo.coinx.service.ConfirmationTokenService;
import jakarta.inject.Inject;

import java.util.Objects;

/**
 * @author :  Ugochukwu Vigo Obia
 * @project : coinx
 * @date :  10/02/2023
 * @email : obiaugochukwu@gmail.com, obiaugochukwu@icloud.com
 */
public class ConfirmationTokenServiceImpl implements ConfirmationTokenService {


    @Inject
    private ConfirmationTokenRepository confirmationTokenRepository;

    @Override
    public void saveConfirmationToken(ConfirmationToken confirmationToken) {
        confirmationTokenRepository.saveConfirmationToken(confirmationToken);
    }

    @Override
    public ConfirmationToken findByToken(String token) throws ResourceNotFoundException {
        ConfirmationToken confirmationToken = confirmationTokenRepository.findByToken(token);
        if(Objects.isNull(confirmationToken)){
            throw new ResourceNotFoundException("Invalid confirmation token");
        }
        return confirmationToken;
    }

    @Override
    public int updateConfirmationToken(ConfirmationToken confirmationToken) throws ResourceNotFoundException, InvalidRequestException {
        ConfirmationToken confirmationToken1 = findByToken(confirmationToken.getToken());
        if(Objects.nonNull(confirmationToken.getConfirmedAt())){
            throw new InvalidRequestException("Token has already been used");
        }
        return confirmationTokenRepository.updateConfirmationToken(confirmationToken);
    }
}
