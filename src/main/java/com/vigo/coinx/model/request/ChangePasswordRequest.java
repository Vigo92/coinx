package com.vigo.coinx.model.request;

import lombok.Data;

/**
 * @author :  Ugochukwu Vigo Obia
 * @project : coinx
 * @date :  10/02/2023
 * @email : obiaugochukwu@gmail.com, obiaugochukwu@icloud.com
 */

@Data
public class ChangePasswordRequest {

    private String email;
    private String newPassword;

}
