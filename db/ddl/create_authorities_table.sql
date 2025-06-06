-- 权限表创建脚本
CREATE TABLE authorities (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(50) NOT NULL,
  authority VARCHAR(50) NOT NULL
);

-- 测试权限
INSERT INTO authorities (username, authority)
VALUES ('testuser', 'ROLE_USER');
