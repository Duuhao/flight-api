package com.flight.config;

import com.flight.service.CustomUserDetailsService;

import com.flight.filter.JwtAuthenticationFilter;
import com.flight.util.JwtTokenUtil;

/**
 * Spring Security配置类
 * 负责配置应用程序的安全策略，包括：
 * 1. JWT认证
 * 2. 密码加密
 * 3. 权限控制
 * 4. CSRF防护
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomUserDetailsService userDetailsService;

    public SecurityConfig(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
    /**
     * 安全配置类，使用Spring Security 6.x新式配置
     * 主要功能：
     * - 配置JWT认证过滤器
     * - 设置无状态会话管理
     * - 配置权限访问规则
     */

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Bean
    public PasswordEncoder passwordEncoder() {
        // 使用BCrypt强哈希算法加密密码
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        // 配置全局认证管理器，使用自定义UserDetailsService和密码加密器
        auth.userDetailsService(this.userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class).build();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // 配置安全过滤器链
        // 1. 禁用CSRF
        // 2. 设置权限规则
        // 3. 配置无状态会话
        // 4. 添加JWT认证过滤器
        http.csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/auth/**").permitAll()
                .anyRequest().authenticated()
            )
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.addFilterBefore(new JwtAuthenticationFilter(jwtTokenUtil, this.userDetailsService),
            UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}