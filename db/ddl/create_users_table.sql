-- 用户表创建脚本
CREATE TABLE users (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(50) NOT NULL UNIQUE,
  password VARCHAR(100) NOT NULL,
  email VARCHAR(100),
  enabled BOOLEAN DEFAULT TRUE,
  account_non_expired BOOLEAN DEFAULT TRUE,
  account_non_locked BOOLEAN DEFAULT TRUE,
  credentials_non_expired BOOLEAN DEFAULT TRUE,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  membership INT NOT NULL DEFAULT 1
);

-- 测试用户(密码:test123)
INSERT INTO users (username, password, email) 
VALUES ('testuser', '$2a$10$N9qo8uLOickgx2ZMRZoMy.MrqK3X1Wp4eQK7Yn9p/4J3d0lQYybSW', 'test@example.com');
