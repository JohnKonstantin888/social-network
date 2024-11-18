package com.example.socialnetwork.entity;


import com.example.socialnetwork.dto.HobbyDTO;
import com.example.socialnetwork.dto.UserDTO;
import com.example.socialnetwork.util.DTOTransformer;
import com.example.socialnetwork.util.Gender;
import com.example.socialnetwork.util.URole;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Comment;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@ToString
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ESN_USERS")
public class AppUser implements DTOTransformer<UserDTO> {
    @Id
    @Column(name = "U_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Comment("Первичный ключ")
    private UUID id;

    @Column(name = "U_NAME")
    @Comment("Имя")
    private String username;

    @Column(name = "U_LAST_NAME")
    @Comment("Фамилия")
    private String lastName;

    @Column(name = "U_BIRTH_DATE")
    @Comment("День рождения")
    private LocalDate birthDate;

    @Column(name = "U_GENDER")
    @Enumerated(EnumType.STRING)
    @Comment("Пол")
    private Gender gender;

    @Column(name = "U_CITY")
    @Comment("Город")
    private String city;

    @Column(name = "U_EMAIL")
    @Comment("Email/логин")
    private String email;

    @Column(name = "U_PASS")
    @Comment("Пароль")
    private String password;

    @Column(name = "U_ROLE")
    @Enumerated(EnumType.STRING)
    private URole role = URole.USER_ROLE;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @ToString.Exclude
    private Set<Hobby> hobbies;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppUser user = (AppUser) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public UserDTO transformToDTO() {
        return UserDTO.builder()
                .id(id == null ? null : id.toString())
                .username(username)
                .lastName(lastName)
                .birthDate(birthDate)
                .gender(gender.getDescForFront())
                .city(city)
                .hobbies(hobbies == null ? null : hobbies.stream().map(Hobby::transformToDTO).map(HobbyDTO::getName).collect(Collectors.joining(", ")))
                .role(role)
                .password(password)
                .email(email)
                .build();
    }
}

