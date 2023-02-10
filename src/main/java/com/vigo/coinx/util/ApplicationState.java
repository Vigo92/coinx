package com.vigo.coinx.util;

import jakarta.enterprise.context.SessionScoped;
import lombok.Data;

import java.io.Serializable;

/**
 * @author :  Ugochukwu Vigo Obia
 * @project : coinx
 * @date :  10/02/2023
 * @email : obiaugochukwu@gmail.com, obiaugochukwu@icloud.com
 */


@SessionScoped
@Data
public class ApplicationState implements Serializable {

    private String key;
}
