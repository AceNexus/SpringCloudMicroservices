package org.ecommerce.account.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class UserRegisterResponse {

    private String userId;
    private String userAccount;
    private String userName;
    private Boolean isActive;
    private Instant createdAt;

}
