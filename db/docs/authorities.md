# 权限表(authorities)文档

## 表结构说明

| 字段名 | 类型 | 是否为空 | 默认值 | 说明 |
|--------|------|----------|--------|------|
| username | VARCHAR(50) | 否 | - | 用户名(关联users表) |
| authority | VARCHAR(50) | 否 | - | 权限名称 |
| created_at | TIMESTAMP | 否 | CURRENT_TIMESTAMP | 创建时间 |

## 索引说明
- 主键: (username, authority) 联合主键
- 外键: fk_authorities_users (关联users表username字段)

## 基础数据
表初始化时会自动插入以下权限数据：
- admin用户拥有ROLE_ADMIN和ROLE_USER权限

## 使用规范
1. 权限名称应以ROLE_开头
2. 用户删除时应同步删除其权限
3. 权限分配应通过后台管理界面操作
