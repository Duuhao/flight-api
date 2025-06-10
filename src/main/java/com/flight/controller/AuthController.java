package com.flight.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import com.flight.dto.LoginRequest;
import com.flight.dto.LoginResponse;
import com.flight.dto.UpdatePasswordRequest;
import com.flight.repository.UserRepository;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import com.flight.dto.UpdatePasswordRequest;
import com.flight.entity.User;
import com.flight.entity.Membership;
import com.flight.repository.MembershipRepository;
import com.flight.service.CustomUserDetailsService;
import com.flight.util.JwtTokenUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.flight.dto.UserInfoResponse;
import org.springframework.transaction.annotation.Transactional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final JwtTokenUtil jwtTokenUtil;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final CustomUserDetailsService customUserDetailsService;
    private final MembershipRepository membershipRepository;

    public AuthController(JwtTokenUtil jwtTokenUtil,
                         UserRepository userRepository,
                         PasswordEncoder passwordEncoder,
                         CustomUserDetailsService customUserDetailsService,
                         MembershipRepository membershipRepository) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.customUserDetailsService = customUserDetailsService;
        this.membershipRepository = membershipRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(loginRequest.getUsername());
        
        if (!passwordEncoder.matches(loginRequest.getPassword(), userDetails.getPassword())) {
            throw new BadCredentialsException("Invalid password");
        }

        User user = userRepository.findByUsername(userDetails.getUsername())
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));
            
        String token = jwtTokenUtil.generateToken(userDetails);
            
        return ResponseEntity.ok(new LoginResponse(
            token,
            user.getId(),
            user.getUsername(),
            user.getEmail(),
            user.getMembership()
        ));
    }

    @GetMapping("/user-info")
    public ResponseEntity<UserInfoResponse> getUserInfo(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.substring(7);
        String username = jwtTokenUtil.extractUsername(token);
        
        User user = userRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));
            
        Membership membership = membershipRepository.findById(user.getMembership())
            .orElseThrow(() -> new RuntimeException("Membership not found"));
            
        return ResponseEntity.ok(new UserInfoResponse(
            user.getUsername(),
            user.getEmail(),
            membership.getId(),
            membership.getName()
        ));
    }

    @PostMapping("/register")
    @Transactional
    public ResponseEntity<String> register(@RequestBody LoginRequest registerRequest) {
        if (userRepository.findByUsername(registerRequest.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body("Username already exists");
        }
        
        User newUser = new User();
        newUser.setUsername(registerRequest.getUsername());
        newUser.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        newUser.setEmail(registerRequest.getEmail());
        newUser.setMembership(1); // Default to Silver membership
        userRepository.save(newUser);
        
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/update-password")
    public ResponseEntity<String> updatePassword(
            @RequestHeader("Authorization") String authHeader,
            @RequestBody UpdatePasswordRequest request) {
        
        String token = authHeader.substring(7);
        String username = jwtTokenUtil.extractUsername(token);
        
        User user = userRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));
            
        if (!request.getNewPassword().equals(request.getConfirmPassword())) {
            return ResponseEntity.badRequest().body("New passwords do not match");
        }
        
        user.setPassword(request.getNewPassword());
        userRepository.save(user);
        
        return ResponseEntity.ok("Password updated successfully");
    }
}
