package com.ntgschool.easystay.Dtos.Response;


import java.util.UUID;

import com.ntgschool.easystay.Entities.Location;
import com.ntgschool.easystay.Entities.Role;
import com.ntgschool.easystay.Entities.UserLocation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse{
    private UUID id;
    private String name;
    private String email;
    private UserLocation location;
    private String phoneNumber;
    private Role role;
}
