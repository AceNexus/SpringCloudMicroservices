-- 20240729 新增 users 資料表
CREATE TABLE users (
    user_id BIGINT(20) NOT NULL AUTO_INCREMENT,
    account VARCHAR(255) NOT NULL,
    password VARCHAR(255),
    email VARCHAR(255),
    phone VARCHAR(20),
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    last_modified_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (user_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 20240729 新增 user_social_accounts 資料表
CREATE TABLE user_social_accounts (
    id BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主鍵 ID',
    user_id BIGINT(20) NOT NULL COMMENT '外鍵，引用 users 表的 user_id',
    provider VARCHAR(255) NOT NULL COMMENT '例如: google, facebook',
    provider_user_id VARCHAR(255) NOT NULL COMMENT '第三方服務中的用戶 ID',
    access_token VARCHAR(255) COMMENT '存取令牌',
    refresh_token VARCHAR(255) COMMENT '刷新令牌（可選）',
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE,
    UNIQUE (provider, provider_user_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用於存儲用戶在不同第三方服務中的登入信息';
