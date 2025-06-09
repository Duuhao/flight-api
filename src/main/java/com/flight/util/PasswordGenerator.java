package com.flight.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordGenerator {
    private static final Logger logger = LoggerFactory.getLogger(PasswordGenerator.class);
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "test123"; // 测试密码
        String encodedPassword = encoder.encode(rawPassword);
        logger.info("原始密码: {}", rawPassword);
        logger.info("BCrypt哈希: {}", encodedPassword);
    }
}
