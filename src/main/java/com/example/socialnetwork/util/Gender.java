package com.example.socialnetwork.util;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Gender {
    MALE("Мужской"),
    FEMALE("Женский");

    private final String descForFront;
}
