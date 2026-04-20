package com.ntgschool.easystay.Dtos.Response;


import com.ntgschool.easystay.Entities.Location;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse{
    private String name;
    private String email;
    private Location location;
    private String phoneNumber;

}
