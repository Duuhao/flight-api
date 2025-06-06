-- 创建城市信息表
CREATE TABLE IF NOT EXISTS cities (
    id INT AUTO_INCREMENT PRIMARY KEY,
    code VARCHAR(3) NOT NULL COMMENT '城市三字码',
    name VARCHAR(50) NOT NULL COMMENT '城市名称',
    country VARCHAR(50) NOT NULL COMMENT '所属国家',
    timezone VARCHAR(50) COMMENT '时区',
    is_active BOOLEAN DEFAULT TRUE COMMENT '是否启用',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    UNIQUE KEY uk_code (code)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='城市信息表';

-- 初始化基础数据
INSERT INTO cities (code, name, country, timezone) VALUES
('PEK', 'Beijing', 'China', 'Asia/Beijing'),
('SHA', 'Shanghai', 'China', 'Asia/Beijing'),
('CAN', 'Guangzhou', 'China', 'Asia/Beijing'),
('SZX', 'Shenzhen', 'China', 'Asia/Beijing'),
('HKG', 'Hong Kong', 'China', 'Asia/Beijing'),
('TYO', 'Tokyo', 'Japan', 'Asia/Tokyo'),
('SEL', 'Seoul', 'South Korea', 'Asia/Seoul'),
('SIN', 'Singapore', 'Singapore', 'Asia/Singapore'),
('SYD', 'Sydney', 'Australia', 'Australia/Sydney'),
('DXB', 'Dubai', 'United Arab Emirates', 'Asia/Dubai');
