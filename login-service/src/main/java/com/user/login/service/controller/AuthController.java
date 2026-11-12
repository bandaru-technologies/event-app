package com.user.login.service.controller;

import com.user.login.service.JwtService;
import com.user.login.service.dto.LoginRequestDto;
import com.user.login.service.dto.LoginResponseDto;
import com.user.login.service.dto.SignupRequestDto;
import com.user.login.service.entity.User;
import com.user.login.service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor

public class AuthController {

  private static final Logger log = LoggerFactory.getLogger(AuthController.class);

  private final AuthenticationManager authenticationManager;
  private final JwtService jwtService;
  private final PasswordEncoder passwordEncoder;
  private final UserRepository userRepository;

//  public AuthController(AuthenticationManager authenticationManager, JwtService jwtService) {
//    this.authenticationManager = authenticationManager;
//    this.jwtService = jwtService;
//  }

  @PostMapping("/signup")
  public ResponseEntity<String> signup(@RequestBody SignupRequestDto request) {

    User user = new User();

    user.setUsername(request.getUsername());
    user.setEmail(request.getEmail());
    user.setPhone(request.getPhone());
    user.setPassword(passwordEncoder.encode(request.getPassword()));
    user.setRole("USER");

    userRepository.save(user);

    return ResponseEntity.ok("User registered successfully");
  }
  @PostMapping("/login")
  public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto request) {

    Authentication authentication =
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()
            )
        );

    String token = jwtService.generateToken(authentication);

    LoginResponseDto response = new LoginResponseDto(token, "Bearer");

    return ResponseEntity.ok(response);
  }
}