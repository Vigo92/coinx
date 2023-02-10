package com.vigo.coinx.model.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * @author :  Ugochukwu Vigo Obia
 * @project : coinx
 * @date :  10/02/2023
 * @email : obiaugochukwu@gmail.com, obiaugochukwu@icloud.com
 */

@Data
public class SignupRequest {


    @Email(message = "Enter a valid email")
    @NotEmpty(message = "This field cannot be blank")
    private String email;

    @NotEmpty(message = "This field cannot be blank")
    @Size(min = 8, message = "Password must contain at least 8 characters")
    private String password;

    @NotEmpty(message = "This field cannot be blank")
    private String firstName;

    @NotEmpty(message = "This field cannot be blank")
    private String lastName;


}
