package com.ntgschool.easystay.Services.impl;

import com.ntgschool.easystay.Dtos.Request.UpdateProfileRequest;
import com.ntgschool.easystay.Entities.Location;
import com.ntgschool.easystay.Entities.Role;
import com.ntgschool.easystay.Entities.User;
import com.ntgschool.easystay.Entities.UserLocation;
import com.ntgschool.easystay.Exceptions.UserAlreadyExistsException;
import com.ntgschool.easystay.Repos.UserRepository;
import com.ntgschool.easystay.Security.UserPrincipal;
import com.ntgschool.easystay.Security.UserPrincipalService;
import com.ntgschool.easystay.Services.AuthenticationService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.*;

import static com.ntgschool.easystay.Entities.Role.USER;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final UserPrincipalService userPrincipalService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiry}")
    private Long jwtExpiryMs;

    @Override
    public UserDetails createUser(String email, String password, String name, String phoneNumber, UserLocation location) {
        if (userRepository.findByEmail(email).isPresent()){
            throw new UserAlreadyExistsException(email) ;
        }
        User user = User.builder()
                .email(email)
                .password(passwordEncoder.encode(password))
                .name(name)
                .phoneNumber(phoneNumber)
                .location(location)
                .role(USER)
                .build();
        userRepository.save(user);

        return new UserPrincipal(user);
    }

    @Override
    public UserDetails createAdminUser(String email, String password, String name, String phoneNumber, UserLocation location) {
        if (userRepository.findByEmail(email).isPresent()){
            throw new UserAlreadyExistsException(email) ;
        }
        User user = User.builder()
                .email(email)
                .password(passwordEncoder.encode(password))
                .name(name)
                .phoneNumber(phoneNumber)
                .location(location)
                .role(Role.ADMIN)
                .build();
        userRepository.save(user);

        return new UserPrincipal(user);
    }

    @Override
    public UserDetails authenticate(String email, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));

        return userPrincipalService.loadUserByUsername(email);
    }

    @Override
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return Jwts.builder()
                .claims(claims)
                .subject(userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + jwtExpiryMs))
                .signWith(getSigningKey())
                .compact();
    }

    private SecretKey getSigningKey(){
        return Keys.hmacShaKeyFor(
                secretKey.getBytes()
        );
    }

    private String getSubject(String token){
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    @Override
    public UserDetails validateToken(String token) {
        String email = getSubject(token);
        return userPrincipalService.loadUserByUsername(email);
    }

    @Override
    public User updateProfile(UpdateProfileRequest request) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));


        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setLocation(request.getLocation());

        userRepository.save(user);

        return user;
    }

    @Override
    public User getUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUser(UUID id) {
        User user = userRepository.findById(id).orElseThrow(
            () -> new UsernameNotFoundException("User with id " + id + " is not found")
        );
        userRepository.delete(user);
    }
}
