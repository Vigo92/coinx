package com.vigo.coinx.model.response;

import com.vigo.coinx.model.dto.UserDTO;
import lombok.Builder;
import lombok.Data;

/**
 * @author :  Ugochukwu Vigo Obia
 * @project : coinx
 * @date :  10/02/2023
 * @email : obiaugochukwu@gmail.com, obiaugochukwu@icloud.com
 */

@Data
@Builder
public class LoginResponse {

    private UserDTO user;

    private String token;
}
