package org.ecommerce.account.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class UserRegisterRequest {

    @NotBlank(message = "UserAccount cannot be null or empty")
    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
    @Pattern(regexp = "^[a-zA-Z0-9\u4e00-\u9fa5]+$", message = "Username can only include letters, numbers, and Chinese characters.")
    private String userAccount;

    @NotBlank(message = "Username cannot be null or empty")
    private String userName;

    @NotBlank(message = "Password cannot be null or empty")
    @Size(min = 6, max = 255, message = "Password must be at least 6 characters")
    private String password;

    private String email;

    private String phone;

    private Boolean isActive = true;
}
