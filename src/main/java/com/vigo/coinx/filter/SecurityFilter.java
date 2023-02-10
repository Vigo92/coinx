package com.vigo.coinx.filter;

import com.vigo.coinx.annotation.Secure;
import com.vigo.coinx.constants.AppConstants;
import com.vigo.coinx.exceptions.NotAuthorizedException;
import com.vigo.coinx.util.ApplicationState;
import com.vigo.coinx.util.SecurityUtil;
import io.jsonwebtoken.Jwts;
import jakarta.annotation.Priority;
import jakarta.inject.Inject;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;
import jakarta.ws.rs.ext.Provider;
import lombok.extern.slf4j.Slf4j;
import java.io.IOException;
import java.security.Key;
import java.security.Principal;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author :  Ugochukwu Vigo Obia
 * @project : coinx
 * @date :  09/02/2023
 * @email : obiaugochukwu@gmail.com, obiaugochukwu@icloud.com
 */

@Provider
@Secure
@Priority(Priorities.AUTHENTICATION)
@Slf4j
public class SecurityFilter implements ContainerRequestFilter , AppConstants {

    @Inject
    private ApplicationState applicationState;
    @Inject
    private SecurityUtil securityUtil;
    @Inject
    private Logger logger;
    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {

        String authHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
        if(Objects.isNull(authHeader) ||! authHeader.startsWith(BEARER)){
            logger.log(Level.SEVERE,"Wrong or no authorization header found {0}", authHeader);
            throw new NotAuthorizedException();
        }
        String token = authHeader.substring(BEARER.length()).trim();
        try{
            Key key = securityUtil.generateKey(applicationState.getKey());
            Jwts.parser().setSigningKey(key).parseClaimsJws(token);
            SecurityContext securityContext = requestContext.getSecurityContext();
            requestContext.setSecurityContext(new SecurityContext() {
                @Override
                public Principal getUserPrincipal() {
                    return () -> Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody().getSubject();
                }
                @Override
                public boolean isUserInRole(String role) {
                    return securityContext.isUserInRole(role);
                }
                @Override
                public boolean isSecure() {
                    return securityContext.isSecure();
                }
                @Override
                public String getAuthenticationScheme() {
                    return securityContext.getAuthenticationScheme();
                }
            });
            logger.log(Level.INFO, "Token parsed successfully");
        }
        catch (Exception e){
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
        }
    }
}
