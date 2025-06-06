# 航班表(flights)文档

## 表功能说明
航班表存储系统所有航班的基本信息，包括航班号、航空公司、起降机场、时间、价格和座位等信息。

## 字段详细说明

| 字段名 | 类型 | 必填 | 默认值 | 说明 |
|--------|------|------|--------|------|
| id | BIGINT | 是 | 自动生成 | 主键，自增长 |
| flight_number | VARCHAR(20) | 是 | - | 航班号，如CA123 |
| airline | VARCHAR(50) | 是 | - | 航空公司名称 |
| departure_airport | VARCHAR(10) | 是 | - | 出发机场三字码 |
| arrival_airport | VARCHAR(10) | 是 | - | 到达机场三字码 |
| departure_time | DATETIME | 是 | - | 计划起飞时间 |
| arrival_time | DATETIME | 是 | - | 计划到达时间 |
| economy_price | DECIMAL(10,2) | 是 | - | 经济舱票价(元) |
| business_price | DECIMAL(10,2) | 是 | - | 商务舱票价(元) |
| available_economy_seats | INT | 是 | - | 经济舱可用座位数 |
| available_business_seats | INT | 是 | - | 商务舱可用座位数 |
| created_at | TIMESTAMP | 否 | CURRENT_TIMESTAMP | 记录创建时间 |
| updated_at | TIMESTAMP | 否 | CURRENT_TIMESTAMP | 记录更新时间 |

## 使用规范
1. 航班号格式：2位航空公司代码+3-4位数字
2. 机场代码使用IATA三字码标准
3. 时间统一使用UTC时间存储
4. 经济舱和商务舱价格保留2位小数
5. 商务舱价格应为经济舱价格的1.5倍

## 索引说明
1. 主键索引: id (PRIMARY)
2. 复合索引: departure_airport, arrival_airport, departure_time
3. 航班号索引: flight_number

## 数据规范
1. airline字段存储航空公司全称
2. departure_airport/arrival_airport字段存储机场三字码
