package com.ntgschool.easystay.Controllers;


import com.ntgschool.easystay.Dtos.Request.AuthenticationRequest;
import com.ntgschool.easystay.Dtos.Request.CreateAccountRequest;
import com.ntgschool.easystay.Dtos.Request.UpdateProfileRequest;
import com.ntgschool.easystay.Dtos.Response.AuthenticationResponse;
import com.ntgschool.easystay.Dtos.Response.UserResponse;
import com.ntgschool.easystay.Entities.User;
import com.ntgschool.easystay.Mappers.UserMapper;
import com.ntgschool.easystay.Services.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static java.util.stream.Collectors.toList;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth/")
public class AuthController {

    private final AuthenticationService authenticationService;
    private final UserMapper userMapper;

    @Value("${jwt.expiry}")
    private Long expiresAt;

    @PostMapping("login")
    public ResponseEntity<AuthenticationResponse> login(
            @RequestBody @Valid AuthenticationRequest request
    ) {
        UserDetails user = authenticationService.authenticate(request.getEmail(), request.getPassword());

        String token = authenticationService.generateToken(user);

        return ResponseEntity.ok(
                AuthenticationResponse.builder()
                        .token(token)
                        .expiresAt(expiresAt)
                        .build()
        );
    }

    @PostMapping("signup")
    public ResponseEntity<AuthenticationResponse> signup(@RequestBody CreateAccountRequest request) {
        UserDetails user = authenticationService.createUser(
                request.getEmail(),
                request.getPassword(),
                request.getName(),
                request.getPhoneNumber(),
                request.getLocation()
        );
        return ResponseEntity.ok(
                AuthenticationResponse.builder()
                        .token(authenticationService.generateToken(user))
                        .expiresAt(expiresAt)
                        .build()
        );
    }

    @PostMapping("admin")
    public ResponseEntity<AuthenticationResponse> createAdmin(@RequestBody CreateAccountRequest request) {
        UserDetails user = authenticationService.createAdminUser(
                request.getEmail(),
                request.getPassword(),
                request.getName(),
                request.getPhoneNumber(),
                request.getLocation()
        );
        return ResponseEntity.ok(
                AuthenticationResponse.builder()
                        .token(authenticationService.generateToken(user))
                        .expiresAt(expiresAt)
                        .build()
        );
    }

    @PutMapping("profile")
    public ResponseEntity<UserResponse> updateProfile(@RequestBody UpdateProfileRequest request) {
        User user = authenticationService.updateProfile(request);
        return ResponseEntity.ok(userMapper.toUserDto(user));
    }

    @DeleteMapping("users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable UUID id){
        authenticationService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("profile")
    public ResponseEntity<UserResponse> getProfile() {
        return ResponseEntity.ok(userMapper.toUserDto(authenticationService.getUser()));
    }

    @GetMapping("users")
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        return ResponseEntity.ok(
            authenticationService.getAllUsers().stream()
                .map(userMapper::toUserDto)
                .collect(toList())
        );
    }

}
