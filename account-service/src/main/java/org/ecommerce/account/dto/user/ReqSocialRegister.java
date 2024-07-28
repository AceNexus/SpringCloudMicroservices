package org.ecommerce.account.dto.user;

import lombok.Data;

@Data
public class ReqSocialRegister {
    private String provider; // 例如: 'google', 'facebook'
    private String providerUserId; // 第三方服務中的用戶 ID
    private String email;
    private String accessToken; // 存取令牌
    private String refreshToken; // 刷新令牌（可選）
}
