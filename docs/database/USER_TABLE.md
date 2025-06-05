# 用户认证表结构文档

## users 表

### DDL语句
```sql
CREATE TABLE users (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(50) NOT NULL UNIQUE,
  password VARCHAR(100) NOT NULL,
  email VARCHAR(100),
  enabled BOOLEAN DEFAULT TRUE,
  account_non_expired BOOLEAN DEFAULT TRUE,
  account_non_locked BOOLEAN DEFAULT TRUE,
  credentials_non_expired BOOLEAN DEFAULT TRUE,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

### 字段说明
| 字段名 | 类型 | 必填 | 说明 |
|--------|------|------|------|
| id | BIGINT | 是 | 主键，自增长 |
| username | VARCHAR(50) | 是 | 用户名，唯一 |
| password | VARCHAR(100) | 是 | BCrypt加密后的密码 |
| email | VARCHAR(100) | 否 | 用户邮箱 |
| enabled | BOOLEAN | 否 | 账户是否启用，默认true |
| account_non_expired | BOOLEAN | 否 | 账户是否过期，默认true |
| account_non_locked | BOOLEAN | 否 | 账户是否锁定，默认true |
| credentials_non_expired | BOOLEAN | 否 | 凭证是否过期，默认true |
| created_at | TIMESTAMP | 否 | 创建时间，默认当前时间 |

## authorities 表

### DDL语句
```sql
CREATE TABLE authorities (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(50) NOT NULL,
  authority VARCHAR(50) NOT NULL,
  FOREIGN KEY (username) REFERENCES users(username)
);
```

### 字段说明
| 字段名 | 类型 | 必填 | 说明 |
|--------|------|------|------|
| id | BIGINT | 是 | 主键，自增长 |
| username | VARCHAR(50) | 是 | 关联users表的用户名 |
| authority | VARCHAR(50) | 是 | 权限名称，如ROLE_USER |

## 示例数据
```sql
-- 测试用户(密码:test123)
INSERT INTO users (username, password, email) 
VALUES ('testuser', '$2a$10$N9qo8uLOickgx2ZMRZoMy.MrqK3X1Wp4eQK7Yn9p/4J3d0lQYybSW', 'test@example.com');

-- 用户权限
INSERT INTO authorities (username, authority)
VALUES ('testuser', 'ROLE_USER');
```

## 使用说明
1. 此表结构兼容Spring Security的认证体系
2. 密码必须使用BCrypt算法加密存储
3. 权限名称应以ROLE_前缀开头