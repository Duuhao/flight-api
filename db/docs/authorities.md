# 权限表(authorities)文档

## 表功能说明
权限表存储用户的角色和权限信息，与Spring Security框架兼容，实现基于角色的访问控制(RBAC)。

## 字段详细说明

| 字段名 | 类型 | 必填 | 默认值 | 说明 |
|--------|------|------|--------|------|
| id | BIGINT | 是 | 自动生成 | 主键，自增长 |
| username | VARCHAR(50) | 是 | - | 关联users表的用户名 |
| authority | VARCHAR(50) | 是 | - | 权限名称，格式为ROLE_XXX |

## 使用规范
1. 权限名称应以ROLE_前缀开头
2. 常用角色：
   - ROLE_USER: 普通用户
   - ROLE_ADMIN: 管理员
   - ROLE_STAFF: 工作人员
3. 用户删除时应级联删除其权限

## 索引说明
1. 主键索引: id (PRIMARY)
