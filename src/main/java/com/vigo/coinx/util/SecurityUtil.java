package com.vigo.coinx.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.core.UriInfo;
import org.apache.shiro.authc.credential.DefaultPasswordService;
import org.apache.shiro.authc.credential.PasswordService;
import org.apache.shiro.codec.Hex;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Sha512Hash;
import org.apache.shiro.util.ByteSource;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author :  Ugochukwu Vigo Obia
 * @project : coinx
 * @date :  10/02/2023
 * @email : obiaugochukwu@gmail.com, obiaugochukwu@icloud.com
 */


@RequestScoped
public class SecurityUtil {
    PasswordService passwordService = new DefaultPasswordService();

    public String encryptText(String plainText){
        return passwordService.encryptPassword(plainText);
    }

    public Key generateKey(String key){
        return new SecretKeySpec(key.getBytes(),0, key.getBytes().length,"DES");
    }

   public boolean passwordsMatch(String dbStoredHashedPassword, String saltText, String clearTextPassword) {
       ByteSource salt = ByteSource.Util.bytes(Hex.decode(saltText));
       String hashedPassword = hashAndSaltPassword(clearTextPassword,salt);
       return hashedPassword.equals(dbStoredHashedPassword);
   }


    public Map<String, String> hashPassword(String clearTextPassword) {
        ByteSource salt = getSalt();
        Map<String, String> credMap = new HashMap<>();
        credMap.put("hashedPassword", hashAndSaltPassword(clearTextPassword, salt));
        credMap.put("salt", salt.toHex());
        return credMap;


    }

    private String hashAndSaltPassword(String clearTextPassword, ByteSource salt) {
        return new Sha512Hash(clearTextPassword, salt, 2000000).toHex();
    }

    private ByteSource getSalt() {
        return new SecureRandomNumberGenerator().nextBytes();
    }

    public Date toDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public String getToken(String email, UriInfo uriInfo){
        Key key = generateKey(email);
        String token = Jwts.builder().setSubject(email).setIssuer(uriInfo.getAbsolutePath().toString())
                .setIssuedAt(new Date()).setExpiration(toDate(LocalDateTime.now().plusMinutes(15)))
                .signWith(SignatureAlgorithm.HS512,key).setAudience(uriInfo.getBaseUri().toString()).compact();
        return token;
    }
}

