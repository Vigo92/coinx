package com.vigo.coinx.model.response;

import com.vigo.coinx.model.constant.Status;
import lombok.Builder;
import lombok.Data;

/**
 * @author :  Ugochukwu Vigo Obia
 * @project : coinx
 * @date :  10/02/2023
 * @email : obiaugochukwu@gmail.com, obiaugochukwu@icloud.com
 */


@Builder
@Data
public class SignupResponse {

    private String message;

    private String responseCode;

    private Status status;
}
