package com.sbb1.user;

import lombok.Getter;

@Getter
public enum UserRole {
    ADMIN("ROLE_ADMIN"),
    USER("RELE_USER");

    UserRole(String value) {
        this.value = value;
    }

    private String value;
}
