-- 20240804 新增 ecommerce_account 資料表
CREATE DATABASE ecommerce_account
DEFAULT CHARACTER SET utf8mb4
DEFAULT COLLATE utf8mb4_unicode_ci;

USE ecommerce_account;

-- 20240804 新增 users 資料表
CREATE TABLE users (
    user_id CHAR(36) NOT NULL,
    user_account VARCHAR(50) NOT NULL UNIQUE,
    user_name VARCHAR(50) NOT NULL,
    password_hash VARCHAR(255),
    email VARCHAR(255) NOT NULL,
    email_verified BOOLEAN DEFAULT FALSE,
    phone VARCHAR(20),
    phone_verified BOOLEAN DEFAULT FALSE,
    is_active BOOLEAN DEFAULT TRUE,
    last_login TIMESTAMP NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    PRIMARY KEY (user_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 20240804 新增 user_profiles 資料表
CREATE TABLE user_profiles (
    user_id CHAR(36) NOT NULL,
    avatar_url CHAR(36) COMMENT '用戶頭像，對應檔案系統中的UUID',
    birth_date DATE,
    gender CHAR(1) COMMENT '用戶性別：M (男性), F (女性), O (其他), U (未指定)',
    location VARCHAR(255),
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    PRIMARY KEY (user_id),
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 20240804 新增 user_auth_providers 資料表
CREATE TABLE user_auth_providers (
    auth_id CHAR(36) NOT NULL,
    user_id CHAR(36) NOT NULL,
    provider VARCHAR(50) NOT NULL COMMENT '第三方服務提供者，例如：Google, Facebook',
    provider_user_id VARCHAR(255) NOT NULL COMMENT '第三方服務提供者的用戶唯一標識',
    access_token VARCHAR(255) COMMENT '第三方服務提供者返回的存取憑證',
    refresh_token VARCHAR(255) COMMENT '第三方服務提供者返回的刷新憑證',
    token_expiry TIMESTAMP COMMENT '存取憑證的過期時間',
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    PRIMARY KEY (auth_id),
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE,
    UNIQUE KEY unique_provider_user (provider, provider_user_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 20240804 新增 roles 資料表
CREATE TABLE roles (
    role_id INT AUTO_INCREMENT PRIMARY KEY,
    role_name VARCHAR(50) UNIQUE NOT NULL,
    description TEXT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 20240803 新增 user_roles 資料表
CREATE TABLE user_roles (
    user_id CHAR(36),
    role_id INT,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE,
    FOREIGN KEY (role_id) REFERENCES roles(role_id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 20240804 新增 login_history 資料表
CREATE TABLE login_history (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id CHAR(36) NOT NULL,
    login_time TIMESTAMP NOT NULL,
    ip_address VARCHAR(45),
    device_type VARCHAR(20) NOT NULL,
    app_version VARCHAR(20),
    os_version VARCHAR(20),
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 20240804 新增 password_reset 資料表
-- 使用流程: a.用戶請求重置密碼
--          b.系統生成一個唯一的 reset_token
--          c.將 reset_token 和相關信息存入 password_reset 表
--          d.系統發送重置連結（包含 reset_token）的郵件或簡訊給用戶
--          e.用戶點擊連結後，系統驗證 reset_token 的有效性
--          f.如果有效允許用戶重置密碼
--          g.密碼重置後，刪除對應的 password_reset 記錄
CREATE TABLE password_reset (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id CHAR(36),
    reset_token VARCHAR(255) NOT NULL,
    expires_at TIMESTAMP NOT NULL,
    created_at TIMESTAMP NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;