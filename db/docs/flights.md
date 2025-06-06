# 航班信息表(flights)文档

## 表结构说明

| 字段名 | 类型 | 是否为空 | 默认值 | 说明 |
|--------|------|----------|--------|------|
| id | BIGINT | 否 | 自动增长 | 航班ID |
| flight_number | VARCHAR(20) | 否 | - | 航班号 |
| airline | VARCHAR(50) | 否 | - | 航空公司 |
| departure_airport | VARCHAR(10) | 否 | - | 出发机场三字码 |
| arrival_airport | VARCHAR(10) | 否 | - | 到达机场三字码 |
| departure_time | DATETIME | 否 | - | 计划起飞时间 |
| arrival_time | DATETIME | 否 | - | 计划到达时间 |
| economy_price | DECIMAL(10,2) | 否 | - | 经济舱价格 |
| business_price | DECIMAL(10,2) | 否 | - | 商务舱价格 |
| available_economy_seats | INT | 否 | - | 可用经济舱座位数 |
| available_business_seats | INT | 否 | - | 可用商务舱座位数 |
| created_at | TIMESTAMP | 否 | CURRENT_TIMESTAMP | 创建时间 |
| updated_at | TIMESTAMP | 否 | CURRENT_TIMESTAMP | 更新时间 |

## 索引说明
- 主键: id
- 普通索引: 
  * idx_departure_arrival (出发机场,到达机场,起飞时间)
  * idx_flight_number (航班号)
- 外键:
  * fk_flights_departure (关联cities表)
  * fk_flights_arrival (关联cities表)

## 基础数据
表初始化时会自动插入10条测试航班数据，包含：
- 北京(PEK)和上海(SHA)之间的往返航班
- 中国主要航空公司(Air China, China Eastern等)的航班
- 不同时间段和价格的航班信息

## 使用规范
1. 机场代码必须使用IATA标准三字码
2. 价格单位为人名币(CNY)
3. 座位数更新需使用事务保证一致性
4. 航班状态变更需记录操作日志
