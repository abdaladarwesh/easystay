package com.ntgschool.easystay.Services;

import com.ntgschool.easystay.Dtos.Request.UpdateProfileRequest;
import com.ntgschool.easystay.Entities.Location;
import com.ntgschool.easystay.Entities.User;
import com.ntgschool.easystay.Entities.UserLocation;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface AuthenticationService {

    UserDetails createUser(String email, String password, String name, String phoneNumber, UserLocation location);

    UserDetails createAdminUser(String email, String password, String name, String phoneNumber, UserLocation location);

    UserDetails authenticate(String email, String password);

    String generateToken(UserDetails userDetails);

    UserDetails validateToken(String Token);

    User updateProfile(UpdateProfileRequest request);

    User getUser();

    List<User> getAllUsers();

    void deleteUser(UUID id);
}
