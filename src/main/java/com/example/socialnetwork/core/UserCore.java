package com.example.socialnetwork.core;

import com.example.socialnetwork.dto.UserDTO;
import com.example.socialnetwork.entity.AppUser;
import com.example.socialnetwork.entity.Hobby;
import com.example.socialnetwork.exception.*;
import com.example.socialnetwork.repository.HobbyRepository;
import com.example.socialnetwork.repository.UserRepository;
import com.example.socialnetwork.util.Gender;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserCore {
    private final UserRepository userRepository;
    private final HobbyRepository hobbyRepository;
    private final PasswordEncoder passwordEncoder;

    public List<UserDTO> findAllUsers() {
        return userRepository.findAll().stream()
                .map(AppUser::transformToDTO)
                .toList();
    }

    public UserDTO findUserById(String id) {
        if (StringUtils.isNotBlank(id)) {
            UUID userId;
            try {
                userId = UUID.fromString(id);
            } catch (IllegalArgumentException e) {
                throw new IncorrectUserIdException(id);
            }
            return userRepository.findById(userId)
                .map(AppUser::transformToDTO).orElseThrow(() -> new UserNotFoundException("id", id));
        }
        throw new EmptyUserIdForSearchException();
    }

    @Transactional
    public void createUser(UserDTO dto) {
        if (dto != null) {
            if (StringUtils.isBlank(dto.getEmail())) {
                throw new EmptyRequiredFieldException("Login");
            }
            AppUser user = null;
            if (StringUtils.isNotBlank(dto.getId())) {
                Optional<AppUser> optionalUser = userRepository.findById(UUID.fromString(dto.getId()));
                if (optionalUser.isPresent()) {
                    user = optionalUser.get();
                }
            } else {
                Optional<AppUser> optional = userRepository.findByEmail(dto.getEmail());
                if (optional.isPresent()) {
                    throw new UserWithLoginAlreadyExistsException(dto.getEmail());
                }
                user = new AppUser();
            }
            assert user != null;
            user = userRepository.save(user
                    .setUsername(dto.getUsername())
                    .setLastName(dto.getLastName())
                    .setBirthDate(dto.getBirthDate() != null ? dto.getBirthDate() : null)
                    .setGender(StringUtils.isNotBlank(dto.getGender()) ? Gender.valueOf(dto.getGender()) : Gender.MALE)
                    .setCity(dto.getCity())
                    .setEmail(dto.getEmail())
                    .setPassword(passwordEncoder.encode(dto.getPassword()))
                    .setRole(dto.getRole())
            );
            AppUser finalUser = user;
            hobbyRepository.saveAll(
                    Arrays.stream(dto.getHobbies().split("\s,\s"))
                            .map(name -> new Hobby()
                                    .setName(name)
                                    .setUser(finalUser))
                            .toList()
            );
        } else {
            throw new IncorrectRequestException();
        }
    }
}
