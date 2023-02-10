package com.vigo.coinx.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.NamedQuery;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Ugochukwu Obia Vigo
 * @project coinx
 * @date 09/02/2023
 * @email obiaugochukwu@gmail.com, obiaugochukwu@icloud.com
 */

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@NamedQuery(name = AppUser.FIND_USER_BY_EMAIL, query = "select u from AppUser u  where u.email = :email ")
public class AppUser extends BaseEntity {

    public static final String FIND_USER_BY_EMAIL = "User.findUserByEmail";

    private String email;

    private String password;

    private String salt;
    private String firstName;
    private String lastName;

}
