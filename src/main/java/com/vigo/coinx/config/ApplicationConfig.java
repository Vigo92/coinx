package com.vigo.coinx.config;

import com.vigo.coinx.controller.AdminController;
import com.vigo.coinx.controller.AuthController;
import com.vigo.coinx.controller.UserController;
import com.vigo.coinx.exceptions.mapper.*;
import jakarta.annotation.security.DeclareRoles;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.security.enterprise.authentication.mechanism.http.BasicAuthenticationMechanismDefinition;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

import java.util.HashSet;
import java.util.Set;

/**
 * @author :  Ugochukwu Vigo Obia
 * @project : coinx
 * @date :  10/02/2023
 * @email : obiaugochukwu@gmail.com, obiaugochukwu@icloud.com
 */

@ApplicationPath("api/v1")
@ApplicationScoped
@DeclareRoles({"ADMIN","USER"})
@BasicAuthenticationMechanismDefinition(realmName = "userRealm")
public class ApplicationConfig extends Application {


    @Override
    public Set<Class<?>> getClasses() {
       Set<Class<?>> resources = new HashSet<>();
       addRestResourceClasses(resources);
       return resources;
    }

    public void addRestResourceClasses(Set<Class<?>> resources){
        resources.add(AuthController.class);
        resources.add(AdminController.class);
        resources.add(InvalidCredentialExceptionMapper.class);
        resources.add(InvalidRequestExceptionMapper.class);
        resources.add(ResourceNotFoundExceptionMapper.class);
        resources.add(NotAuthorizedExceptionMapper.class);
        resources.add(UserAlreadyExistsExceptionMapper.class);
    }
}
