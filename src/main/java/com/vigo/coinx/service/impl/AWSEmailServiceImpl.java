package com.vigo.coinx.service.impl;

import com.vigo.coinx.model.entity.AppUser;
import com.vigo.coinx.model.entity.ConfirmationToken;
import com.vigo.coinx.service.EmailService;
import jakarta.ws.rs.core.UriInfo;

/**
 * @author :  Ugochukwu Vigo Obia
 * @project : coinx
 * @date :  10/02/2023
 * @email : obiaugochukwu@gmail.com, obiaugochukwu@icloud.com
 */
public class AWSEmailServiceImpl implements EmailService {
    @Override
    public void sendEmail(AppUser appUser, ConfirmationToken confirmationToken, UriInfo uriInfo) {

    }
}
