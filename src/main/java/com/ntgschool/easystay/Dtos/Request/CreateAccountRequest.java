package com.ntgschool.easystay.Dtos.Request;

import com.ntgschool.easystay.Entities.Location;
import com.ntgschool.easystay.Entities.UserLocation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateAccountRequest {
    private String name;

    private String email;

    private String password;

    private UserLocation location;

    private String phoneNumber;
}
