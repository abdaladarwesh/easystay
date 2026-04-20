package com.ntgschool.easystay.Dtos.Request;

import com.ntgschool.easystay.Entities.Location;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateProfileRequest {
    private String name;
    private String email;
    private String phoneNumber;
    private Location location;
}