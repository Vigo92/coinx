package com.vigo.coinx.model.dto;

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
public class UserDTO {

    private String email;
    private String firstName;
    private String lastName;
}
