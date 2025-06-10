# 用户表(users)文档

## 表功能说明
用户表用于存储系统所有用户的认证信息，与Spring Security框架兼容，支持账户状态管理和密码加密存储。

## 字段详细说明

| 字段名 | 类型 | 必填 | 默认值 | 说明 |
|--------|------|------|--------|------|
| id | BIGINT | 是 | 自动生成 | 主键，自增长 |
| username | VARCHAR(50) | 是 | - | 用户名，必须唯一 |
| password | VARCHAR(100) | 是 | - | BCrypt加密后的密码，长度建议60-80字符 |
| email | VARCHAR(100) | 否 | NULL | 用户邮箱，用于通知和找回密码 |
| membership | INT | 是 | 1 | 会员等级(1-4对应Silver/Gold/Platinum/Diamond) |
| enabled | BOOLEAN | 否 | TRUE | 账户是否启用，false表示禁用 |
| account_non_expired | BOOLEAN | 否 | TRUE | 账户是否过期，false将无法登录 |
| account_non_locked | BOOLEAN | 否 | TRUE | 账户是否锁定，false将无法登录 |
| credentials_non_expired | BOOLEAN | 否 | TRUE | 凭证(密码)是否过期，false需修改密码 |
| created_at | TIMESTAMP | 否 | CURRENT_TIMESTAMP | 账户创建时间 |

## 使用规范
1. 密码必须使用BCryptPasswordEncoder加密存储
2. 用户名应做唯一性校验
3. 禁用账户应设置enabled=false而非直接删除
4. 重要操作应检查账户状态字段

## 索引说明
1. 主键索引: id (PRIMARY)
2. 唯一索引: username (自动创建)
## 关联关系
- 一对多关联authorities表(通过username字段)
