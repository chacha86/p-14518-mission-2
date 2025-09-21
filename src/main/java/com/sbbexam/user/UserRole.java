package com.sbbexam.user;

import lombok.Getter;

@Getter
public enum UserRole {
    ADMIN("ROLE_ADMIN"),
    USER("ROME_USER");

    UserRole(String value) {
        this.value = value;
    }

    private String value;
}
