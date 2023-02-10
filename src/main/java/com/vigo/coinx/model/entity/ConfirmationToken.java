package com.vigo.coinx.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author :  Ugochukwu Vigo Obia
 * @project : coinx
 * @date :  10/02/2023
 * @email : obiaugochukwu@gmail.com, obiaugochukwu@icloud.com
 */


@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@NamedQuery(name = ConfirmationToken.FIND_BY_TOKEN, query = "select c from ConfirmationToken  c where c.token = :token")
public class ConfirmationToken extends BaseEntity{

    public static final String FIND_BY_TOKEN = "ConfirmationToken.findConfirmationTokenByEmail";
    private String token;
    private LocalDateTime createdAt;
    private LocalDateTime expiresAt;
    private LocalDateTime confirmedAt;

    @ManyToOne
    private AppUser appUser;


}
