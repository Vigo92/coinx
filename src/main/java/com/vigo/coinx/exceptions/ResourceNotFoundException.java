package com.vigo.coinx.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author :  Ugochukwu Vigo Obia
 * @project : coinx
 * @date :  10/02/2023
 * @email : obiaugochukwu@gmail.com, obiaugochukwu@icloud.com
 */


@Data
@AllArgsConstructor
public class ResourceNotFoundException extends Exception {
    private String message;
}
