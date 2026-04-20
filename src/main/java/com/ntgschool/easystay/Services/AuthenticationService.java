package com.ntgschool.easystay.Services;

import com.ntgschool.easystay.Dtos.Request.UpdateProfileRequest;
import com.ntgschool.easystay.Entities.Location;
import com.ntgschool.easystay.Entities.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public interface AuthenticationService {

    UserDetails createUser(String email, String password, String name, String phoneNumber, Location location);

    UserDetails authenticate(String email, String password);

    String generateToken(UserDetails userDetails);

    UserDetails validateToken(String Token);

    User updateProfile(UpdateProfileRequest request);
}
