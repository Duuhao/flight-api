# 城市信息表(cities)

## 表结构说明

| 字段名 | 类型 | 是否为空 | 默认值 | 说明 |
|--------|------|----------|--------|------|
| id | INT | NO | AUTO_INCREMENT | 主键ID |
| code | VARCHAR(3) | NO | - | 城市三字码(唯一) |
| name | VARCHAR(50) | NO | - | 城市名称 |
| country | VARCHAR(50) | NO | - | 所属国家 |
| timezone | VARCHAR(50) | YES | - | 时区信息 |
| is_active | BOOLEAN | YES | TRUE | 是否启用 |
| created_at | TIMESTAMP | NO | CURRENT_TIMESTAMP | 创建时间 |
| updated_at | TIMESTAMP | NO | CURRENT_TIMESTAMP | 更新时间 |

## 索引说明
- 主键: id
- 唯一索引: uk_code (code字段)

## 基础数据
表初始化时会自动插入以下城市数据：
- 北京(PEK)、上海(SHA)、广州(CAN)、深圳(SZX)
- 香港(HKG)、东京(TYO)、首尔(SEL)
- 新加坡(SIN)、悉尼(SYD)、迪拜(DXB)

## 使用规范
1. 所有航班相关的城市必须先在cities表中注册
2. 城市三字码(code)必须使用IATA标准代码
3. 禁用城市只需将is_active设为FALSE
4. 时区信息使用IANA时区格式
