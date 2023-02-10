package com.vigo.coinx.config;

import jakarta.enterprise.inject.spi.InjectionPoint;
import jakarta.ws.rs.Produces;

import java.util.logging.Logger;

/**
 * @author :  Ugochukwu Vigo Obia
 * @project : coinx
 * @date :  10/02/2023
 * @email : obiaugochukwu@gmail.com, obiaugochukwu@icloud.com
 */
public class LoggerConfig {

    @Produces
    public Logger produceLogger(InjectionPoint injectionPoint){
        return Logger.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
    }
}
