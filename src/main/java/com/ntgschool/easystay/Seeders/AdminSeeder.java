package com.ntgschool.easystay.Seeders;

import com.ntgschool.easystay.Entities.Location;
import com.ntgschool.easystay.Entities.Role;
import com.ntgschool.easystay.Entities.User;
import com.ntgschool.easystay.Entities.UserLocation;
import com.ntgschool.easystay.Repos.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class AdminSeeder implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.findByEmail("admin@easystay.com").isEmpty()) {
            UserLocation defaultLocation = new UserLocation();
            defaultLocation.setDisplayName("");
            defaultLocation.setHouseNumber(21L);
            defaultLocation.setLocation(
                Location.builder()
                .city("cario")
                .country("Egypt")
                .latitude(BigDecimal.valueOf(3232.43434))
                .longitude(BigDecimal.valueOf(3232.43434))
                .build()
            );
            defaultLocation.setNeighbourhood("Ainshams");
            defaultLocation.setRoad("road");
            defaultLocation.setSuburb("sd");
            
            User admin = User.builder()
                    .name("Super Admin")
                    .email("admin@easystay.com")
                    .password(passwordEncoder.encode("admin123"))
                    .phoneNumber("0000000000")
                    .location(defaultLocation)
                    .role(Role.ADMIN)
                    .createdAt(LocalDateTime.now())
                    .build();
            userRepository.save(admin);
            System.out.println("Default admin created: admin@easystay.com / admin123");
        }
    }
}
