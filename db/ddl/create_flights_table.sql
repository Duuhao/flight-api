-- 创建航班信息表
CREATE TABLE IF NOT EXISTS flights (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '航班ID',
    flight_number VARCHAR(20) NOT NULL COMMENT '航班号',
    airline VARCHAR(50) NOT NULL COMMENT '航空公司',
    departure_airport VARCHAR(10) NOT NULL COMMENT '出发机场三字码',
    arrival_airport VARCHAR(10) NOT NULL COMMENT '到达机场三字码',
    departure_time DATETIME NOT NULL COMMENT '计划起飞时间',
    arrival_time DATETIME NOT NULL COMMENT '计划到达时间',
    economy_price DECIMAL(10,2) NOT NULL COMMENT '经济舱价格',
    business_price DECIMAL(10,2) NOT NULL COMMENT '商务舱价格',
    available_economy_seats INT NOT NULL COMMENT '可用经济舱座位数',
    available_business_seats INT NOT NULL COMMENT '可用商务舱座位数',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    
    INDEX idx_departure_arrival (departure_airport, arrival_airport, departure_time),
    INDEX idx_flight_number (flight_number)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='航班信息表';

-- 初始化测试数据
INSERT INTO flights VALUES
(1, 'CA123', 'Air China', 'PEK', 'SHA', '2025-06-10 08:00:00', '2025-06-10 10:30:00', 1200.00, 1800.00, 150, 30, NOW(), NOW()),
(2, 'MU456', 'China Eastern', 'SHA', 'PEK', '2025-06-10 12:00:00', '2025-06-10 14:30:00', 1500.00, 2250.00, 120, 20, NOW(), NOW()),
(3, 'CZ345', 'China Southern', 'PEK', 'SHA', '2025-06-10 09:30:00', '2025-06-10 12:00:00', 1100.00, 1650.00, 180, 25, NOW(), NOW()),
(4, 'HU789', 'Hainan Airlines', 'SHA', 'PEK', '2025-06-10 14:00:00', '2025-06-10 16:30:00', 1300.00, 1950.00, 100, 15, NOW(), NOW()),
(5, 'CA456', 'Air China', 'PEK', 'SHA', '2025-06-10 18:00:00', '2025-06-10 20:30:00', 1400.00, 2100.00, 90, 10, NOW(), NOW()),
(6, 'MU789', 'China Eastern', 'SHA', 'PEK', '2025-06-10 20:00:00', '2025-06-10 22:30:00', 1250.00, 1875.00, 110, 18, NOW(), NOW()),
(7, 'CZ678', 'China Southern', 'PEK', 'SHA', '2025-06-11 08:30:00', '2025-06-11 11:00:00', 1150.00, 1725.00, 160, 22, NOW(), NOW()),
(8, 'HU123', 'Hainan Airlines', 'SHA', 'PEK', '2025-06-11 10:00:00', '2025-06-11 12:30:00', 1350.00, 2025.00, 95, 12, NOW(), NOW()),
(9, 'CA789', 'Air China', 'PEK', 'SHA', '2025-06-11 13:00:00', '2025-06-11 15:30:00', 1450.00, 2175.00, 85, 8, NOW(), NOW()),
(10, 'MU123', 'China Eastern', 'SHA', 'PEK', '2025-06-11 16:00:00', '2025-06-11 18:30:00', 1050.00, 1575.00, 200, 35, NOW(), NOW());
