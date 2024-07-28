package org.ecommerce.account.dto.user;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class ReqUsersRegister {

    @NotBlank(message = "帳號不能為空")
    private String account;

    @NotBlank(message = "密碼不能為空")
    private String password;

    @Email(message = "無效的電子郵件格式")
    private String email;

    private String phone;

}
