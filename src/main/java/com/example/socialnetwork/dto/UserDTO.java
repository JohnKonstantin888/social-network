package com.example.socialnetwork.dto;

import com.example.socialnetwork.util.URole;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalDate;

@Getter
@Setter
@FieldDefaults(level=AccessLevel. PRIVATE)
@AllArgsConstructor
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor
@Jacksonized
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDTO {
    String id;
    String username;
    String lastName;
    LocalDate birthDate;
    String gender;
    String city;
    String email;
    String password;
    URole role = URole.USER_ROLE;
    String hobbies;
}
