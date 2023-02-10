package com.vigo.coinx.model.request;

import jakarta.validation.constraints.Email;
import lombok.Data;

/**
 * @author :  Ugochukwu Vigo Obia
 * @project : coinx
 * @date :  10/02/2023
 * @email : obiaugochukwu@gmail.com, obiaugochukwu@icloud.com
 */

@Data
public class LoginRequest {

    @Email(message = "Enter a valid email!")
    private String username;
    private String password;
}
