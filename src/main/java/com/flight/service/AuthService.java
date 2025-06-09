package com.flight.service;

import com.flight.dto.LoginRequest;
import com.flight.dto.LoginResponse;
import com.flight.dto.UserInfoResponse;
import com.flight.entity.User;
import com.flight.repository.UserRepository;
import com.flight.util.JwtTokenUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;

@Service
public class AuthService {

    private final JwtTokenUtil jwtTokenUtil;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final CustomUserDetailsService customUserDetailsService;

    public AuthService(JwtTokenUtil jwtTokenUtil,
                     UserRepository userRepository,
                     PasswordEncoder passwordEncoder,
                     CustomUserDetailsService customUserDetailsService) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.customUserDetailsService = customUserDetailsService;
    }

    public LoginResponse authenticate(LoginRequest loginRequest) {
        User user = customUserDetailsService.loadUserByUsername(loginRequest.getUsername());
        
        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("Invalid password");
        }

        String token = jwtTokenUtil.generateToken(user);
        
        return new LoginResponse(
            token,
            user.getId(),
            user.getUsername(),
            user.getEmail(),
            user.getMembership()
        );
    }

    public UserInfoResponse getUserInfo(String token) {
        String username = jwtTokenUtil.extractUsername(token);
        User user = userRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));
            
        return new UserInfoResponse(
            user.getUsername(),
            user.getEmail(),
            user.getMembership()
        );
    }

    public String registerUser(LoginRequest registerRequest) {
        if (userRepository.findByUsername(registerRequest.getUsername()).isPresent()) {
            throw new IllegalArgumentException("Username already exists");
        }
        
        User newUser = new User();
        newUser.setUsername(registerRequest.getUsername());
        newUser.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        newUser.setEmail(registerRequest.getEmail());
        newUser.setMembership(1); // Default to Silver membership
        userRepository.save(newUser);
        
        return "User registered successfully";
    }

    public String updatePassword(String token, String newPassword, String confirmPassword) {
        if (!newPassword.equals(confirmPassword)) {
            throw new IllegalArgumentException("New passwords do not match");
        }
        
        String username = jwtTokenUtil.extractUsername(token);
        User user = userRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));
            
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
        
        return "Password updated successfully";
    }
}
