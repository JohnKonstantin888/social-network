package com.example.socialnetwork.core;

import com.example.socialnetwork.dto.UserDTO;
import com.example.socialnetwork.util.URole;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Slf4j
public class UserDetailsImpl implements UserDetails {
    private String id;
    private String username;
    private String lastName;
    private LocalDate birthDate;
    private String gender;
    private String city;
    private String email;
    private String password;
    private URole role;
    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl(String id,
                           String username,
                           String lastName,
                           LocalDate birthDate,
                           String gender,
                           String city,
                           String email,
                           String password,
                           URole role,
                           Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.gender = gender;
        this.city = city;
        this.email = email;
        this.password = password;
        this.role = role;
        this.authorities = authorities;
    }

    public static UserDetailsImpl build(UserDTO userDTO) {
        log.info("UserDetailsImpl.build : {}", userDTO);
        List<GrantedAuthority> authorityList = List.of(new SimpleGrantedAuthority(userDTO.getRole().name()));
        return new UserDetailsImpl(
                userDTO.getId(),
                userDTO.getUsername(),
                userDTO.getLastName(),
                userDTO.getBirthDate(),
                userDTO.getGender(),
                userDTO.getCity(),
                userDTO.getEmail(),
                userDTO.getPassword(),
                userDTO.getRole(),
                authorityList
        );
    }

    @Override
    public String getUsername() {
        return email;
    }

    public String getName() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }
}
