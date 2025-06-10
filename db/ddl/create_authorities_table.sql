-- 创建权限表
CREATE TABLE IF NOT EXISTS authorities (
    username VARCHAR(50) NOT NULL COMMENT '用户名',
    authority VARCHAR(50) NOT NULL COMMENT '权限名称',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (username, authority),
    CONSTRAINT fk_authorities_users FOREIGN KEY (username) REFERENCES users(username)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户权限表';

-- 初始化管理员权限
INSERT INTO authorities (username, authority) VALUES
('admin', 'ROLE_ADMIN'),
('admin', 'ROLE_USER');
