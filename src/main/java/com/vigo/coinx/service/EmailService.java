package com.vigo.coinx.service;

import com.vigo.coinx.model.entity.AppUser;
import com.vigo.coinx.model.entity.ConfirmationToken;
import jakarta.ws.rs.core.UriInfo;

/**
 * @author :  Ugochukwu Vigo Obia
 * @project : coinx
 * @date :  10/02/2023
 * @email : obiaugochukwu@gmail.com, obiaugochukwu@icloud.com
 */
public interface EmailService {


    void sendEmail(AppUser appUser, ConfirmationToken confirmationToken, UriInfo uriInfo);
}
