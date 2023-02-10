package com.vigo.coinx.config;

import jakarta.annotation.sql.DataSourceDefinition;
import jakarta.ejb.Stateless;

/**
 * @author :  Ugochukwu Vigo Obia
 * @project : coinx
 * @date :  10/02/2023
 * @email : obiaugochukwu@gmail.com, obiaugochukwu@icloud.com
 */


@DataSourceDefinition(
        name = "java:app/coinx/db",
        className = "com.mysql.cj.Driver",
        url = "jdbc:mysql://127.0.0.1:3306/?user=root",
        user = "root",
        password = "Springboot1914@"
)
@Stateless
public class PersistenceConfig {
}
